import torch
from torch import nn

class Zhangyun(nn.Module):
    def __init__(self):
        super().__init__()

    '''
    本神经网络把输入加一后输出
    '''
    def forward(self,input):
        output=input+1
        return output

'''
使用神经网络模板Zhangyun() 创建 出神经网络 zhangyun
'''
zhangyun = Zhangyun()
x=torch.tensor(1.0)
'''
网友问：为啥tudui(x)，的x可以直接传入forward方法里面？？？？？？不是要调用方法才行吗？？？？
    网友答：因为继承的nn.Module中forword方法是__call__()方法的实现，可调用对象会调用__call__()方法
    网友答：因为__call__()方法的实现_call_impl()中调用了forward()方法
'''
output=zhangyun(x)
print(output)