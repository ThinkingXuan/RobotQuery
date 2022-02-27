#!/usr/bin/env Python
# coding=utf-8

# conv_path = 'F:/chinese/dgk_lost_conv-master/dgk_shooter_min.conv'
#
# input_file = open(conv_path, "r", encoding='utf-8')
#
# for i in input_file.readlines():
#     print(i)

import tensorflow as tf

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


def read_data(source_path, target_path, max_size=None):
    data_set = [[] for _ in buckets]
    with tf.gfile.GFile(source_path, mode="r") as source_file:
        with tf.gfile.GFile(target_path, mode="r") as target_file:
            source, target = source_file.readline(), target_file.readline()
            print("source:", source, "target:", target)
            counter = 0
            while source and target and (not max_size or counter < max_size):
                counter += 1
                source_ids = [int(x) for x in source.split()]
                print("source_ids:", source_ids)
                target_ids = [int(x) for x in target.split()]
                print("target_ids:", target_ids)
                target_ids.append(EOS_ID)
                for bucket_id, (source_size, target_size) in enumerate(buckets):
                    if len(source_ids) < source_size and len(target_ids) < target_size:
                        data_set[bucket_id].append([source_ids, target_ids])
                        print(data_set)
                        break
                source, target = source_file.readline(), target_file.readline()
                print("source1:", source, "target1:", target)

    return data_set


read_data(test_encode_vec, test_decode_vec)