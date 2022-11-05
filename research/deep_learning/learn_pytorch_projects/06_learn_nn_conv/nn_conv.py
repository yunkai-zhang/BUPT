# -*- coding: utf-8 -*-
# 作者：小土堆
# 公众号：土堆碎念

import torch
import torch.nn.functional as F

'''
网友问：和transform中的totensor有什么区别
    网友答：这个数数据类型转换，Totensor是把图片类型转换成tensor
高赞网友：卷积核的大小是自己设置的，卷积核上每个位置相当于一个权重w，比如一个3*3的卷积核，就是9个w，训练网络的目的就是学习这9个权值
'''
input = torch.tensor([[1, 2, 0, 3, 1],
                      [0, 1, 2, 3, 1],
                      [1, 2, 1, 0, 0],
                      [5, 2, 3, 1, 1],
                      [2, 1, 0, 1, 1]])

kernel = torch.tensor([[1, 2, 1],
                       [0, 1, 0],
                       [2, 1, 0]])

'''
因为是5*5像素矩阵的图片，所以channel是1；因为只有一张图片，所以batchsize设置为1；宽高仍设置为5*5

网友：batchsize是指这批数据的数据个数，这里就一个矩阵，可以看成是只有一张5*5的灰度图像
'''
input = torch.reshape(input, (1, 1, 5, 5))
kernel = torch.reshape(kernel, (1, 1, 3, 3))

print(input.shape)
print(kernel.shape)

output = F.conv2d(input, kernel, stride=1)
print(output)

'''
因为stride为2比1打，相当于做卷积的次数变少了，卷积得到的结果output2的大小也就变小了

网友问：为什么变成四维张量了？
    网友答：因为reshap后就是四为张量了，输入的就是一个四维，输出的自然也是四维的

高赞网友：输出的维数与输入维数有公式的，一般输出=输入-卷积核大小/stride +1

高赞网友：3*3的卷积后基本就将周围八个像素点聚集成了一个中心像素点
'''
output2 = F.conv2d(input, kernel, stride=2)
print(output2)

'''
不padding时是3*3的输出，padding后是5*5的输出；这是因为3*3的上下左右都padding了一排像素，所以输出结果也可以上下左右各增加一排
'''
output3 = F.conv2d(input, kernel, stride=1, padding=1)
print(output3)