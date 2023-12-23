import os
import sys
import requests
client_id = "upgw1ry1ag"
client_secret = "v090IllWVNDYNMkmIXpw6TVQFSQiQEHoc89Jf7PM"


url = "https://naveropenapi.apigw.ntruss.com/vision-obj/v1/detect" 
files = {'image': open('street.jpg', 'rb')}
headers = {'X-NCP-APIGW-API-KEY-ID': client_id, 'X-NCP-APIGW-API-KEY': client_secret }
response = requests.post(url,  files=files, headers=headers)
rescode = response.status_code

if(rescode==200):
    print (response.text)
else:
    print("Error Code:" + str(rescode))