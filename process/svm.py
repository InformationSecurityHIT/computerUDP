from sklearn import svm
import numpy as np
import pickle
import os

#一个人一个features.txt文件，每次做二分类的时候传入两个文件夹
def train_svm(zero_path,one_path,features_dim):
    zero_data = np.loadtxt(zero_path,dtype=float,delimiter=' ')
    one_data = np.loadtxt(one_path,dtype=float,delimiter=' ')
    data = np.vstack((zero_data,one_data))
    name ='hhh' #name这个属性可以从两个标签拼接得到
    x,y = np.split(data,indices_or_sections=(features_dim,),axis=1)
    clf = svm.SVC()
    clf.fit(x,y)
    s = pickle.dumps(clf)
    f = open('D:\\Lab\\xinan_new\\DZH\\svm_model\\svm'+name+'.model','wb+')
    f.write(s)
    f.close()
    print("Done")
def test_svm(svm_name,feature):
    files = os.listdir()
    for file in files:
        f2=open(svm_name,'rb')
        s2=f2.read()
        model1=pickle.loads(s2)
        predicted = model1.predict(feature)
        print(predicted)

if __name__ == "__main__":
    # train_svm('1.txt','2.txt',6)
    test = np.array([[9.8,2.5,3,4,5,6]])
    test_svm('D:\\Lab\\xinan_new\\DZH\\svm_model\\svmhhh.model',test)
