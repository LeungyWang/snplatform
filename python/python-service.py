import json
from flask import Flask, Response
import pandas as pd
import numpy as np
import pymysql
from sqlalchemy import create_engine
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D  # 空间三维画图
app = Flask(__name__)
@app.route("/health")
def health():
    result = {'status': 'UP'}
    return Response(json.dumps(result), mimetype='application/json')
@app.route('/predict/<float:n>/<float:p>/<float:k>',methods=['GET'])
def data_predict(n,p,k):
    clustering_df = get_data()
    clustering_data = np.array(clustering_df[['nitrogen','phosphorus','potassium']])
    # plt.scatter(clustering_data['nitrogen'], clustering_data['phosphorus'],clustering_data['potassium'])    #绘制原始数据
    #创建一个K-均值聚类对象
    kmeans = KMeans(n_clusters=3, random_state=10)
    #拟合算法
    kmeans.fit(clustering_data)
    #获取聚类标签
    label_pred = kmeans.labels_
    centers = kmeans.cluster_centers_
    x_new = np.array([n,p,k]).reshape(1, -1)
    soil_type = int(kmeans.predict(x_new))
    result = {'soiltype': soil_type}
    return Response(json.dumps(result), mimetype='application/json')
#从数据库中取并清洗训练数据
def get_data():
    # 初始化数据库连接，使用pymysql模块
    # MySQL的用户：root, 密码:密码, 端口：3306,数据库：snpt_land
    engine = create_engine("mysql+pymysql://root:134679@localhost:3306/snpt_land",encoding='utf-8')
    # 查询语句，选出tnutrition_output表中的所有数据
    sql = """select * from nutrition_output"""
    # read_sql_query的两个参数: sql语句， 数据库连接
    df = pd.read_sql_query(sql,con=engine)
    df = pd.DataFrame(df,columns=['nitrogen','phosphorus','potassium'])
    df = df.replace('', np.nan, regex=True)
    #数据清洗
    #     df = df.fillna(df.mean()) # 用平均数代替,选择各自列的均值替换缺失值
    df = df.dropna() #清除缺失数据
    df = pd.DataFrame(df,dtype = 'float')
    return df
app.run(port=3000, host='0.0.0.0')