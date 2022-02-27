file = open('test.conv', 'r', encoding='utf-8')


conv = []

with open('test.conv', 'r', encoding='utf-8') as f:
    one_conv = []

    for str in f.readlines():
        str = str.strip('\n').replace('/', "")

        if str == '':
            continue
        if str[0] == 'E':
            if one_conv:
                conv.append(one_conv)
            one_conv = []
        elif str[0] == 'M':
            one_conv.append(str.split(" ")[1])

    print(conv)

