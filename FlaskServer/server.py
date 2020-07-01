# -*- coding: utf-8 -*-
from flask import Flask, render_template, request

import datetime
import tensorflow as tf
import numpy as np

app = Flask(__name__)
appp = Flask(__name__)

# 플레이스 홀더를 설정합니다.
X = tf.placeholder(tf.float32, shape=[None, 13])
Y = tf.placeholder(tf.float32, shape=[None, 1])

W = tf.Variable(tf.random_normal([13, 1]), name="weight")
b = tf.Variable(tf.random_normal([1]), name="bias")

# 가설을 설정합니다.
hypothesis = tf.matmul(X, W) + b

# 저장된 모델을 불러오는 객체를 선언합니다.
saver = tf.train.Saver()
model = tf.global_variables_initializer()

# 세션 객체를 생성합니다.
sess = tf.Session()
sess.run(model)

# 저장된 모델을 세션에 적용합니다.
save_path = "./model/saved.cpkt"
saver.restore(sess, save_path)

#아리마

import pandas as pd

from datetime import datetime, timedelta

series = pd.read_csv('./static/ARIMA/time.csv', header=0, index_col=0, squeeze=True)
# 일정 구간 추출 (너무 많으면 계산이 어려움)
series = series.truncate(before='2010-05-20', after='2020-06-17')
series.plot(figsize=(20, 5))

import matplotlib.pyplot as plt
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from pandas.plotting import autocorrelation_plot

fig, ax = plt.subplots(figsize=(20, 5))
plot_acf(series, ax=ax)
fig, ax = plt.subplots(figsize=(20, 5))
plot_pacf(series, ax=ax)
fig, ax = plt.subplots(figsize=(20, 5))
autocorrelation_plot(series, ax=ax)


import matplotlib.pyplot as plt
import pandas as pd
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf

fig, ax = plt.subplots(figsize=(20, 5))
diff_1=series.diff(periods=1).iloc[1:]
diff_1.plot(ax=ax)
fig, ax = plt.subplots(figsize=(20, 5))
plot_acf(diff_1, ax=ax)
fig, ax = plt.subplots(figsize=(20, 5))
plot_pacf(diff_1, ax=ax)


from statsmodels.tsa.arima_model import ARIMA

# (p,d,q) order
model = ARIMA(series, order=(8,1,0))
model_fit = model.fit(trend='c',full_output=True, disp=1)
print(model_fit.summary())

fig, ax = plt.subplots(figsize=(20, 10))
model_fit.plot_predict(ax=ax)

fig = plt.gcf()
fig.savefig('./static/ARIMA/forecast.jpeg')

forecast, stderr, conf_int = model_fit.forecast(steps=10)
print(forecast)

#아리마끝


@app.route("/", methods=['GET', 'POST'])
def index():
    if request.method == 'GET':
        return render_template('index.html')
    if request.method == 'POST':
        # 파라미터를 전달 받습니다.
        avg_temper = float(request.form['avg_temper'])
        low_temper = float(request.form['low_temper'])
        high_temper = float(request.form['high_temper'])
        rainfall = float(request.form['rainfall'])
        avg_ws = float(request.form['avg_ws'])
        avg_rehumi = float(request.form['avg_rehumi'])
        total_sunhine = float(request.form['total_sunhine'])
        total_sunradi = float(request.form['total_sunradi'])
        avg_landtemper = float(request.form['avg_landtemper'])
        low_grasstemper = float(request.form['low_grasstemper'])
        avg_humid = float(request.form['avg_humid'])
        day_soilwater = float(request.form['day_soilwater'])
        CPI = float(request.form['CPI'])

        # 배추 가격 변수를 선언합니다.
        price = 0

        # 입력된 파라미터를 배열 형태로 준비합니다.
        data = ((avg_temper, low_temper, high_temper, rainfall, avg_ws, avg_rehumi, total_sunhine, total_sunradi, avg_landtemper, low_grasstemper, avg_humid, day_soilwater, CPI), )
        arr = np.array(data, dtype=np.float32)

        # 입력 값을 토대로 예측 값을 찾아냅니다.
        x_data = arr[0:13]
        dict = sess.run(hypothesis, feed_dict={X: x_data})

        # 결과 배추 가격을 저장합니다.
        price = dict[0]

        return render_template('index.html', price=price)


@app.route("/arima", methods=['GET', 'POST'])
def po():
    if request.method == 'GET':
        return render_template('index.html')
    if request.method == 'POST':
        csvtime = datetime(2020, 6, 18)
        timenow = datetime.now()

        diff = abs((timenow-csvtime).days)
        count = diff+31

        forecast, stderr, conf_int = model_fit.forecast(steps=count)

    return render_template('index.html', timenow=timenow, price1=forecast[diff], price2=forecast[diff+1], price3=forecast[diff+2], price4=forecast[diff+3], price5=forecast[diff+4], price6=forecast[diff+5], price7=forecast[diff+6]
    , price8=forecast[diff+8]
    , price9=forecast[diff+9]
    , price10=forecast[diff+10]
    , price11=forecast[diff+11]
    , price12=forecast[diff+12]
    , price13=forecast[diff+13]
    , price14=forecast[diff+14]
    , price15=forecast[diff+15]
    , price16=forecast[diff+16]
    , price17=forecast[diff+17]
    , price18=forecast[diff+18]
    , price19=forecast[diff+19]
    , price20=forecast[diff+20]
    , price21=forecast[diff+21]
    , price22=forecast[diff+22]
    , price23=forecast[diff+23]
    , price24=forecast[diff+24]
    , price25=forecast[diff+25]
    , price26=forecast[diff+26]
    , price27=forecast[diff+27]
    , price28=forecast[diff+28]
    , price29=forecast[diff+29]
    , price30=forecast[diff+30])

@app.route("/bootstrap", methods=['GET', 'POST'])
def po2():
        if request.method == 'GET':
            return render_template('index.html')
        if request.method == 'POST':
            return render_template('index.html',bootstrap=1)

@app.route("/linear", methods=['GET', 'POST'])
def po3():
        if request.method == 'GET':
            return render_template('index.html')
        if request.method == 'POST':
            return render_template('index.html',linear=1)

@app.route("/arima2", methods=['GET', 'POST'])
def po4():
        if request.method == 'GET':
            return render_template('index.html')
        if request.method == 'POST':
            return render_template('index.html',arima2=1)

if __name__ == '__main__':
   app.run(host='0.0.0.0', port=8989,debug = True)
