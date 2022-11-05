# -*- coding: utf-8 -*-
# 本类单纯介绍各个损失函数
import torch
from torch.nn import L1Loss
from torch import nn

inputs = torch.tensor([1, 2, 3], dtype=torch.float32)
targets = torch.tensor([1, 2, 5], dtype=torch.float32)

'''
更改后的size为：1batch 1channel 1行 3列
'''
inputs = torch.reshape(inputs, (1, 1, 1, 3))
targets = torch.reshape(targets, (1, 1, 1, 3))

'''
针对L1loss：

设置reduction为sum后，loss=（1-1）+（2-2）+（5-3）=2

这些参数都可以在官网上看到
'''
loss = L1Loss(reduction='sum')
result = loss(inputs, targets)
print(result)

'''
针对mseloss

网友：均方差是RMSE

'''
loss_mse = nn.MSELoss()
result_mse = loss_mse(inputs, targets)
print(result_mse)

'''
针对交叉熵作为loss：

当训练一个分类问题时常用

1. 网友：交叉熵是逻辑回归的损失函数
2. 高赞网友：交叉熵从最大思然估计角度很好理解
3. 网友：三分类应该是：猫，狗，都不是
4. 网友：个人观点：理论上确实相加为一，但实际算法中不同的类别都是分开计算的，一般肯定不为一
5. 网友：要先进行softmax计算才能加起来等于1。直接计算出来的logits， 是一个数， 通过softmax以后才是和为1 的概率。
6. 网友：这个公式是把softmax函数和多分类交叉熵的式子叠加在一起写成这样子了 有不明白的可以分别搜下softmax 和交叉熵各自的公式
7. 高赞网友：这个output相当于网络最终输出logits，然后输入到softmax，得到score，3个类别的score之和为1
8. 高赞网友：交叉熵这个内置函数先将x经过softmax处理之后，再计算误差的

'''
x = torch.tensor([0.1, 0.2, 0.3])
y = torch.tensor([1])
'''
改形状为：1batchsize 3类别
'''
x = torch.reshape(x, (1, 3))
loss_cross = nn.CrossEntropyLoss()
result_cross = loss_cross(x, y)
print(result_cross)