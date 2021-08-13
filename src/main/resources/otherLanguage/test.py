from re import S
from urllib import request
import requests
import json


class Course:
    '课程信息'
    code = ''
    courseStatus = ''
    name = ''
    schemaId = ''
    schoolId = ''
    syllabusId = ''
    time = ''
    type = ''
    def __init__(self, item): 
        self.code = item['courseCode']
        self.courseStatus = "1"
        self.name = item['courseName']
        self.schemaId = "2"
        self.schoolId = "44"
        self.syllabusId = "96"
        self.time = item['time']
        self.type = "1"

data = requests.get("http://localhost:8090/mydemo/import/import")
realData = json.loads(data.text)[0:59]

# print(realData)
url = 'http://localhost:8090/api/course/addCourse'
headers = {'Content-Type': 'application/json', 'token' : ''}

for item in realData:
    course = Course(item)


