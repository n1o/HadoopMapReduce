import random

points = list();
for i in range (10000):

    x = round(random.uniform(1, 1000),1)
    y = round(random.uniform(1, 1000),1)
    z = round(random.uniform(1, 1000),1)
    s = str(x) + ", " + str(y) + ", " + str(z)
    points.append(s)


for i in range (100):
    fileName ="points"+str(i)
    file = open(fileName,"w")
    for i in range (10000):
        f = int(random.uniform(0, 999));
        s = int(random.uniform(0, 999));
        if( f == s):
            continue
        pair = points[f] + ", " + points[s] + "\n"
        file.write(pair)



    



             
