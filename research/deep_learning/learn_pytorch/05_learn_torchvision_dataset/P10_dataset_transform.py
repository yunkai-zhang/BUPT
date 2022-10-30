import torchvision
from torch.utils.tensorboard import SummaryWriter

dataset_transform = torchvision.transforms.Compose([
    torchvision.transforms.ToTensor()
])

'''
我们这使用torchvision自带的数据集CIFAR10；因为我们这没有这个数据集，所以要把download设置为true来下载

如果下载太慢了，可以把下载地址拷贝到迅雷中下载；迅雷可能更快一点，因为可能不从源地址下载而又p2p加速

使用这个数据集是因为它比较下，下载方便
'''
train_set = torchvision.datasets.CIFAR10(root="./dataset", train=True, transform=dataset_transform, download=True)
test_set = torchvision.datasets.CIFAR10(root="./dataset", train=False, transform=dataset_transform, download=True)

'''
打印结果中，第一个数据是tensor对象即一张图片，第二个数据是target即图片对应的分类
'''
print(test_set[0])
# print(test_set.classes)
#
# img, target = test_set[0]
# print(img)
# print(target)
# print(test_set.classes[target])
# img.show()
#
# print(test_set[0])

'''
logs和p10是你创建的保存文件的文件夹，这里程序叫p10所以用p10
'''
writer = SummaryWriter("p10")
for i in range(10):
    img, target = test_set[i]
    print(img)
    writer.add_image("test_set", img, i)

writer.close()