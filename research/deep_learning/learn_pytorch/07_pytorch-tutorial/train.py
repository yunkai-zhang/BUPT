# -*- coding: utf-8 -*-

import torchvision
from torch.utils.tensorboard import SummaryWriter
# 注意model.python和train.python要在一个文件夹底下，这样才能正确引入
from model import *
from torch import nn
from torch.utils.data import DataLoader

'''
准备数据集
'''
train_data = torchvision.datasets.CIFAR10(root="../data", train=True, transform=torchvision.transforms.ToTensor(),
                                          download=True)
test_data = torchvision.datasets.CIFAR10(root="../data", train=False, transform=torchvision.transforms.ToTensor(),
                                         download=True)

# 获得数据集的长度。len==length 长度。
train_data_size = len(train_data)
test_data_size = len(test_data)
# 如果train_data_size=10, 训练数据集的长度为：10
'''
网友：Python3.6 新增了一种 f-字符串格式化，也可以写成print(f"训练数据集的长度为:{train_data_size}")
土堆：.format可以把自身包含的内容替换掉{}中的内容。还有更多打印方式，细节可以了解一下python的字符串部分的内容。
网友：print("训练集的长度为：%s" % (train_data_size)) 
'''
print("训练数据集的长度为：{}".format(train_data_size))
print("测试数据集的长度为：{}".format(test_data_size))


# 利用 DataLoader 来加载数据集
train_dataloader = DataLoader(train_data, batch_size=64)
test_dataloader = DataLoader(test_data, batch_size=64)

# 创建网络模型
'''
网友：问back_grand和loss的，自己去看model的写法，根本不写在这，别隔着抖聪明了
网友：还没开始训练就后向传播交叉熵了是吧
'''
zhangyun = Zhangyun() # 类Zhangyun所代表的模型被放在 model.python文件中，本文件之前已经引入了model.pyrhon文件的全部内容，自然也就引入了类Zhangyun

# 损失函数
'''
因为当前问题是分类问题，所以损失函数可以用交叉熵。
参数使用默认的，不额外显式设置。
'''
loss_fn = nn.CrossEntropyLoss()

# 优化器
'''
1. 这里使用了随机梯度下降作为优化器来根据梯度优化参数。
'''
# learning_rate = 0.01
# 0.01的写法还有这种：1e-2=1 x (10)^(-2) = 1 /100 = 0.01。这样想写0.000000001的话，吧-2改成-10就可以了。
learning_rate = 1e-2
optimizer = torch.optim.SGD(zhangyun.parameters(), lr=learning_rate)

# 设置训练网络的一些参数
# 记录训练的次数
total_train_step = 0
# 记录测试的次数
total_test_step = 0
# 训练的轮数
epoch = 10

# 添加tensorboard
writer = SummaryWriter("../logs_train")

for i in range(epoch):
    print("-------第 {} 轮训练开始-------".format(i+1))

    # 训练步骤开始
    zhangyun.train()# 这个步骤可以不写，因为这步的作用比较小
    for data in train_dataloader:
        imgs, targets = data
        outputs = zhangyun(imgs)
        loss = loss_fn(outputs, targets)

        # 优化器优化模型
        optimizer.zero_grad()# 先要优化器梯度清零
        loss.backward() # 反向传播得到各个参数的梯度
        optimizer.step()# 根据各个参数的梯度，对参数进行优化

        '''
        网友问：不用RELU吗？
        - 网友：这里的交叉商包括了soft max
        - 网友：然后再对softmax值取对数，再使用nllloss进行计算,nllloss会将target转为onehot编码形式
        '''

        total_train_step = total_train_step + 1
        # 让训练集每训练100次做一次打印，避免控制台被训练集的打印刷屏 而导致其他关键打印无法被轻易找到
        if total_train_step % 100 == 0:
            # 这里的 loss.item() 是正规的写法，如果直接写 loss 其实也可以；不过有时候像 变量.item 可以把像tensor变量转化成数字
            print("训练次数：{}, Loss: {}".format(total_train_step, loss.item()))
            writer.add_scalar("train_loss", loss.item(), total_train_step)

    '''
    每次训练完一个epoch后，都会在测试集上跑一遍，看看训练的效果
    '''
    # 测试步骤开始
    zhangyun.eval()# 这个步骤可以不写，因为这步的作用比较小
    total_test_loss = 0
    total_accuracy = 0
    '''
    with torch.no_grad():表示梯度没有了，保证不会在测试过程中对模型进行调优
    '''
    with torch.no_grad():
        for data in test_dataloader:
            imgs, targets = data
            outputs = zhangyun(imgs)
            loss = loss_fn(outputs, targets)
            # loss是一个batch的损失；total_test_loss是整个数据集的总损失！
            total_test_loss = total_test_loss + loss.item()
            accuracy = (outputs.argmax(1) == targets).sum()
            total_accuracy = total_accuracy + accuracy

    '''
    每训练完一个epoch会做如下打印
    - 网友：这个很棒，可以一边训练一边看
    '''
    print("整体测试集上的Loss: {}".format(total_test_loss))
    print("整体测试集上的正确率: {}".format(total_accuracy/test_data_size))
    writer.add_scalar("test_loss", total_test_loss, total_test_step)
    writer.add_scalar("test_accuracy", total_accuracy/test_data_size, total_test_step)
    total_test_step = total_test_step + 1

    '''
    1，土堆：一般每一轮都会保存一下训练结果，使用i来区分不同轮的模型，避免模型之间相互覆盖
    - 网友：每一轮都保存会不会太多了
    - 我：可能是为了结合loss曲线，这样保存所有模型就可以拿到loss最小的模型
    2，网友：验证集的本质就是选择超参数，如果这里当做训练集就是违背了训练集仅仅用于模型评价的准则
    '''
    torch.save(zhangyun, "zhangyun_{}.pth".format(i))
    print("模型已保存")

writer.close()