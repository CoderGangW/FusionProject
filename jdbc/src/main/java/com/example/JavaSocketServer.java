package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


  
public class JavaSocketServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private InputStream in;
    public String logined_admin;
   
    static DAO dao = new DAO();
    
    public void start(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        System.out.println("\n√  JAVA 서버가 " + port +"번 포트에서 실행중입니다.");
        clientSocket = serverSocket.accept();
        System.out.println("\n√  클라이언트 접속됨");
        System.out.println("\n√  IP : " + clientSocket.getInetAddress().getHostName());

        in = clientSocket.getInputStream();

        try{
            while (true) {
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                String response = new String(buffer, 0, bytesRead);
                System.out.println("\n Detected Obj  :  " + response);

                Date nowDate = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");
                String now = simpleDateFormat.format(nowDate);
                
                String insertStr = "INSERT INTO detected(object, date) VALUES (?, ?)";
                try (PreparedStatement pstmt = dao.conn.prepareStatement(insertStr)) {
                    pstmt.setString(1, response);
                    pstmt.setString(2, now);
                    pstmt.executeUpdate();
                    System.out.println("√  데이터 추가 성공 - 검출정보");
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("ERROR : " + e.getMessage());
                }
            }
        } catch(Throwable e) {
            e.printStackTrace();
            clientSocket.close();
            System.out.println("○  소켓서버가 종료되었습니다 ");
        }
    }

    
    // 문자열 공백체크
    static boolean isStringEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // 로그인 시스템
    public Boolean loginCheck(String ID, char[] PW){
        Boolean loginchecking = false;

        if (isStringEmpty(ID)==true){
            JOptionPane.showMessageDialog(null, "사원번호를 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
        } else {
            if( isStringEmpty(String.valueOf(PW)) == true) { 
                JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
            }   else {
                try {
                    dao.stmt = dao.conn.createStatement();
                    String Finduser = "SELECT * FROM admininfo WHERE admin_Num ="+ID;

                    dao.rs = dao.stmt.executeQuery(Finduser);
                    if (dao.rs.next()) {
                        do {
                            if (String.valueOf(PW).equals(dao.rs.getString("password"))) {
                                JOptionPane.showMessageDialog(null, "로그인 되었습니다.", "로그인 성공", 1);
                                logined_admin = dao.rs.getString("admin_name");
                                loginchecking = true;
                                break;
                            } else {
                                loginchecking = false;
                            }
                        } while (dao.rs.next());
                    } else {
                        JOptionPane.showMessageDialog(null, "DATABASE ResultSET ERR", "로그인 실패", 1);
                        loginchecking = false;
                    }
                } catch (Exception e) {
                    System.out.println("ERROR - Socketmodule : " + e.toString());
                } finally {
                    if (dao.rs != null) {
                        try {
                            dao.rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return loginchecking;
    }
    
    // 주문 GUI ActionEvent Trigger Function
    public void ordergui(String name, String phone, String shape , Boolean stamp) {
        String NamefromGUI = name;
        String PhonefromGUI = phone;
        String ShapefromGUI = shape;
        String CustomfromGUI = stamp ? "YES" : "NO";

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");
        String now = simpleDateFormat.format(nowDate);

        if(isStringEmpty(NamefromGUI) == true){
            JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "경고",JOptionPane.WARNING_MESSAGE );
        } else {
            if(isStringEmpty(PhonefromGUI) == true){
                JOptionPane.showMessageDialog(null, "전화번호를 입력해 주세요.", "경고",JOptionPane.WARNING_MESSAGE );
            }else{
                if(isStringEmpty(ShapefromGUI)==true){
                    JOptionPane.showMessageDialog(null, "스타일을 선택해 주세요.", "경고",JOptionPane.WARNING_MESSAGE );
                } else{
                    int ans = JOptionPane.showConfirmDialog(null,
                    "아래 정보가 맞으십니까? \n\n"
                    +"이름 : "+NamefromGUI+"\n"
                    +"전화번호 : "+PhonefromGUI+"\n"
                    +"스타일 : "+ShapefromGUI+"\n"
                    +"커스텀 : "+CustomfromGUI+"\n"
                    , "주문 확인", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (ans == 0){
                        try {
                            dao.stmt = dao.conn.createStatement();
                            dao.rs = dao.stmt.executeQuery("SELECT * FROM materials");
                            dao.rs.next();
                            int shapeFromDB = dao.rs.getInt(ShapefromGUI);
                            if(shapeFromDB >= 1){
                                int setShapeDB = shapeFromDB - 1;
                                String updateStr = "UPDATE materials"+" SET  "+shape+ " = "+setShapeDB+" ";
                                dao.stmt.executeUpdate(updateStr);
                                String insertStr = "INSERT INTO buyerinfo(name, phone, material, custom, date) VALUES(' " +NamefromGUI+ " ' ,' " +PhonefromGUI+ " ' ,' " +ShapefromGUI+ " ' ,' " +CustomfromGUI+ " ' , ' "+now+" ' )";
                                dao.stmt.execute(insertStr);
                                JOptionPane.showMessageDialog(null, "주문에 성공하였습니다!\n 관리자 곧 승인할것입니다.","주문 성공 ✅", JOptionPane.INFORMATION_MESSAGE);
                                System.out.println("√  데이터 추가 성공 - 구매정보");
                            } else{
                                JOptionPane.showMessageDialog(null, "재고가 없습니다.\n 관리자에게 문의하세요.", "재고 오류 ⚠️",JOptionPane.WARNING_MESSAGE );
                            }
                        } catch(Exception e) {
                            System.out.println("ERROR - Ordermodule: " + e.toString());
                        }
                    }
                }
            }
        }
    }

    // Table 정보 가져오기 및 Return DATA
    public String[][] OrderTable() {
        String[][] data = null;
        try {
            dao.stmt = null;
            if (dao.conn != null) {
                dao.stmt = dao.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            }
            if (dao.stmt != null) {
                dao.rs = dao.stmt.executeQuery("SELECT * FROM buyerinfo");
                int rowCount = 0;
                while (dao.rs.next()) {
                    rowCount++;
                }
                ResultSetMetaData rsmd = dao.rs.getMetaData();
                int colCount = rsmd.getColumnCount();
                if (rowCount > 0) {
                    data = new String[rowCount][colCount];
                    dao.rs.beforeFirst();
                    int i = 0;
                    while (dao.rs.next()) {
                        for (int j = 0; j < colCount; j++) {
                            data[i][j] = dao.rs.getString(j + 1);
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR - SQL to Table : " + e.toString());
        }
        return data;
    }
    

    // 선택된 행의 데이터 가져오기
    public String[] getOrderById(String ID) {
        String[] selectedTable = null;
        String selectStr = "SELECT * FROM buyerinfo WHERE idbuyerinfo = ?";
        int intID = Integer.parseInt(ID);
        try (PreparedStatement pstmt = dao.conn.prepareStatement(selectStr)) {
            pstmt.setInt(1, intID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    selectedTable = new String[] {
                        rs.getString("idbuyerinfo"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("material"),
                        rs.getString("custom"),
                        rs.getString("date")
                    };
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR - SelectedTable module : " + e.getMessage());
        }
        return selectedTable;
    }

    //주문정보 삭제
    public void delOrder(String ID) {
        int intID = Integer.parseInt(ID);
        String delStr = "DELETE FROM buyerinfo WHERE idbuyerinfo = ?";
        try (PreparedStatement pstmt = dao.conn.prepareStatement(delStr)){
            pstmt.setInt(1, intID);
            pstmt.executeUpdate();
            System.out.println("\n√  데이터 삭제 성공 - 주문정보");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR - Delete buyerinfo module : " + e.getMessage());
        }
    }
    //취소된 주문 자재 갯수 복구
    public void rollbackOrder(String shape) {
        String nospace = shape.trim();
        String updateShape = "UPDATE materials set " + nospace + " = " + nospace + " + 1 WHERE idmaterials = 1";   
        try (PreparedStatement pstmt = dao.conn.prepareStatement(updateShape)){
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR - Update materials module" + e.getMessage());
        }
    }

    //주문정보 수정
    public void editOrder(String[] editedOrder){
        int intID = Integer.parseInt(editedOrder[0]);
        String updateStr = "UPDATE buyerinfo set name=?,phone=?,material=?,custom=? WHERE idbuyerinfo = ?";
        try (PreparedStatement pstmt = dao.conn.prepareStatement(updateStr)){
            for(int i = 1; i<5; i++){
                pstmt.setString(i, editedOrder[i]);
            }
            pstmt.setInt(5,intID);
            pstmt.executeUpdate();
            System.out.println("\n √  데이터 수정 성공 - 주문정보");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR - Update buyerinfo module" + e.getMessage());
        }
    }
    
    //자재 개수 불러오기
    public int[] load_amount(){
        int[] cnt_amount = null;
        String amtStr = "SELECT * FROM  materials WHERE idmaterials = ?";
        try (PreparedStatement pstmt = dao.conn.prepareStatement(amtStr)) {
            pstmt.setInt(1, 1);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cnt_amount = new int[] {
                        rs.getInt("Square"),
                        rs.getInt("Rectangle"),
                        rs.getInt("Hexagon"),
                        rs.getInt("Octagon")
                    };
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR - SelectedTable module : " + e.getMessage());
        }
        return cnt_amount;
    }
    
    //자재 개수 업데이트
    public void update_mat_amt(int[] Up_amont){
        String update_mat_Str = "UPDATE materials set Square=Square+?,Rectangle=Rectangle+?,Hexagon=Hexagon+?,Octagon=Octagon+? WHERE idmaterials = ?";
        try (PreparedStatement pstmt = dao.conn.prepareStatement(update_mat_Str)){
            for(int i =0; i<4;i++){
                pstmt.setInt(i+1, Up_amont[i]);
            }
            pstmt.setInt(5, 1);
            pstmt.executeUpdate();
            System.out.println("\n√  데이터 수정 성공 - 자재개수");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR - Update materials module" + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        JavaSocketServer server = new JavaSocketServer();
        // MesGui mesgui  = new MesGui();
        OrderGui order = new OrderGui();
        AdminGUI admpage = new AdminGUI();
        

        // mesgui.Mes_gui();
        dao.database();
        admpage.admin_page();
        order.order_pannel();
        server.start(9999);
    }
}

