from torch.utils.tensorboard import SummaryWriter
import numpy as np
from PIL import Image

writer = SummaryWriter("../logs")
img_path= "../01_learn_dataset/dataset/train/bees/39747887_42df2855ee.jpg";
img_PIL = Image.open(img_path)
# 用opencv拿到的图片格式本身就是numpy格式的；但是如果用PIL的image拿到的图片，就需要转化格式为numpy格式
img_array=np.array(img_PIL)
# 图片 高 宽 channel（一般为3表示RGB）的，如果channel放在最后面，需要设置dataformaytes；这是可以从add_image的源码注释知道的
writer.add_image("test_add_image",img_array,2,dataformats='HWC')
# y=2*x
for i in range(100):
    writer.add_scalar("title为y=2x", 3*i, i)

writer.close()