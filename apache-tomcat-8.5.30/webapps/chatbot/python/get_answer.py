#!/usr/bin/env python
import os
from predict import main
import codecs
from sys import argv

f = codecs.open("/root/apache-tomcat-8.5.30/webapps/chatbot/python/new.txt", 'w', 'utf-8')
answer = main(argv[1])
f.write(answer)
f.write('\n')
f.close()

