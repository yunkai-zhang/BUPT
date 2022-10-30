import torch
from torch import nn

class Zhangyun(nn.Module):# 自己写的定义模型的结构的类 需要继承nn.module
    def __init__(self):
        super(Zhangyun,self).__init__()# 自定义类的初始化，直接调用父类的nn.Module来初始化
        # 把网络定义放到一个sequantial中，这样避免在forward中写的很麻烦
        self.model=nn.Sequential(
            # 卷积层：ctrl+p可以看到参数；参数分别是 inputchannel outputchannel kernalsize stride用默认的1 padding设置为2保证卷积后尺寸不变（可以计算出来的）
            nn.Conv2d(3,32,5,1,2),
            # 最大池化层：kernalsize设置为2，其他的像stride和padding就用默认的就可以，因为正好符合
            nn.MaxPool2d(2),
            # 卷积层
            nn.Conv2d(32, 32, 5, 1, 2),
            # 最大池化
            nn.MaxPool2d(2),
            # 卷积层
            nn.Conv2d(32,64,5,1,2),
            # 最大池化
            nn.MaxPool2d(2),
            # 展平的同时实现一个线性层：
            # 展平：展平后的大小应该是64*4*4的大小，因为本层把这么大的图像像素展平了。
            # 线性层：参数分别是inputfeature outputfeature
            nn.Flatten(),
            nn.Linear(64*4*4,64),
            # 线性层：通过这个线性层后，就可以得到10分类的输出。注意线性层就是全连接层就是经典的神经网络！
            nn.Linear(64,10)
        )
    def forward(self,x):
        x=self.model(x)
        return x

'''
很多人喜欢在这写个main 并测试网络的正确性
https://www.bilibili.com/video/BV1hE411t7RN/?p=27&spm_id_from=pageDriver&vd_source=8be62db2c8e19174231a64770292e191
14.39
'''
if __name__ == '__main__': # pycharm中右键run，或者这里界面点击左边的绿色三角都能run（常用）
    # 创造出网络模型
    zhangyun=Zhangyun()
    # 为了考察网络的正确性，给一个输入的尺寸，看输出的尺寸是否正确
    input=torch.ones((64,3,32,32))
    output=zhangyun(input)
    '''
    输出为：
    torch.Size([64, 10])
    这体现每个有64个图片，每个图片都有一个10维度的onehot来表征预测结果
    '''
    print(output.shape)