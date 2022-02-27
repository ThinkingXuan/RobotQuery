import tensorflow as tf  # 0.12
import os
import numpy as np
import math

from pythonRobot.test2 import seq2seq_model

PAD_ID = 0
GO_ID = 1
EOS_ID = 2
UNK_ID = 3

train_encode_vec = 'train_encode.vec'
train_decode_vec = 'train_decode.vec'
test_encode_vec = 'test_encode.vec'
test_decode_vec = 'test_decode.vec'

# 词汇表大小5000
vocabulary_encode_size = 5000
vocabulary_decode_size = 5000

buckets = [(5, 10), (10, 15), (20, 25), (40, 50)]
layer_size = 256  # 每层大小
num_layers = 3  # 层数
batch_size = 64


# 读取*dencode.vec和*decode.vec数据（数据还不算太多, 一次读人到内存）
def read_data(source_path, target_path, max_size=None):
    data_set = [[] for _ in buckets]
    with tf.gfile.GFile(source_path, mode="r") as source_file:
        with tf.gfile.GFile(target_path, mode="r") as target_file:
            source, target = source_file.readline(), target_file.readline()
            counter = 0
            while source and target and (not max_size or counter < max_size):
                counter += 1
                source_ids = [int(x) for x in source.split()]
                target_ids = [int(x) for x in target.split()]
                target_ids.append(EOS_ID)

                # buckets = [(5, 10), (10, 15), (20, 25), (40, 50)]


                for bucket_id, (source_size, target_size) in enumerate(buckets):
                    print("bucket_id:", bucket_id, "source_size", source_size, "source_size", source_size)
                    print(len(source_ids), source_size, len(target_ids), target_size)
                    if len(source_ids) < source_size and len(target_ids) < target_size:
                        print("成立")
                        data_set[bucket_id].append([source_ids, target_ids])
                        break
                source, target = source_file.readline(), target_file.readline()
    return data_set


date_set = read_data(test_encode_vec, test_decode_vec)
from pprint import pprint  # pretty-printer

pprint(date_set)
