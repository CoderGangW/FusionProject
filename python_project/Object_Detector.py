import cv2
import numpy as np
import time
import keyboard
from keras.models import load_model
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'
import tensorflow as tf
import socket
import serial

# 이것은 파이썬이 오브젝트를 인식합니다.

ser = serial.Serial('COM3', 9600) # 포트와 시리얼 통신 속도 설정

tf.keras.utils.disable_interactive_logging()
currentPath = os.getcwd()

HOST = 'localhost'
PORT = 9999

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    os.system('cls')
    print('Connected to Java server')
    print("Loading - [===       ] - 학습모델 로드중...")

    # Keras 모델 로드
    model = load_model(currentPath + "\\python_project\\trained_model\\keras_model.h5",compile=False)

    # 클래스 라벨 정의
    with open(currentPath + "\\python_project\\trained_model\\labels.txt", 'r', encoding='UTF-8') as f:
        class_labels = f.read().splitlines()
        os.system('cls')
        print("Loading - [======    ] - 웹캠 모듈 불러오는중...")

    # 웹캠에서 프레임 읽어오기
    cap = cv2.VideoCapture(0)
    os.system('cls')
    print("Loading - [==========] - 웹캠에서 프레임 읽기 준비중...")
    while True:
        ret, frame = cap.read()                             
        
        # 이미지 전처리
        img = cv2.resize(frame, (224, 224),interpolation=cv2.INTER_AREA)
        img = np.expand_dims(img, axis=0)
        
        img = np.asarray(img, dtype=np.float32).reshape(1, 224, 224, 3)

        # Normalize the image array
        img = (img / 127.5) - 1

        # Predicts the model
        prediction = model.predict(img)
        index = np.argmax(prediction)
        class_name = class_labels[index]
        confidence_score = prediction[0][index]
        
        cv_text = (class_name[2:]+" : "+ str(np.round(confidence_score * 100))[:-2]+ "%")
        cv2.putText(frame, cv_text,(10,25), cv2.FONT_HERSHEY_DUPLEX, 1, (0, 255, 0),2)
        

        if ser.in_waiting > 0:
            message = ser.readline().decode('utf-8').rstrip()

            if message == "Button pressed":
                # Print prediction and confidence score
                os.system('cls')
                print(" - 검출된 물체 :", class_name[2:], end=" | ")
                print("신뢰도 :", str(np.round(confidence_score * 100))[:-2], "%")
                s.send(class_name[2:].encode())

        # 결과 프레임 출력
        cv2.imshow('frame', frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            os.system('cls')
            print("▶  프로그램을 종료합니다.")
            break

    cap.release()
    cv2.destroyAllWindows()