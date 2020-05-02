from bs4 import BeautifulSoup
import requests
import pymysql
import json
#获得新闻链接列表
def get_url(issue,page):
    url="http://www.farmer.com.cn/xbpd/"+issue+"/NewsList_"+str(page)+".json"
    header={
        "User-Agent": 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36'
    }
    r=requests.get(url=url,headers=header)
    r.encoding =r.apparent_encoding
    json_data = json.loads(r.text)['info']
    urlList = []
    for item in json_data:
        url = item['url']
        urlList.append(url)
    return urlList
#获得新闻内容
def get_content(url):
    header={
        "User-Agent": 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36'
    }
    r=requests.get(url=url,headers=header)
    r.encoding =r.apparent_encoding
    text = r.text
    soup = BeautifulSoup(text,'html.parser')
    #新闻标题
    title = soup.find(name="h1", attrs={"class" :"article-title"}).string
    #新闻出处
    if soup.find(name="span", attrs={"class" :"tag-text tag-text-source"}) is not None:
        source = soup.find(name="span", attrs={"class" :"tag-text tag-text-source"}).string
    else:
        source = ""
    #新闻内容
    article = str(soup.find(name="div", attrs={"class" :"article-main"}))
    return [title,article,source]

# print(get_content("http://www.farmer.com.cn/2020/04/09/99851044.html"))

#存储新闻到数据库中
def insertIntoMysql(newsinfo):
    mysql_host = 'localhost'
    mysql_db = 'snpt_news'
    mysql_user = 'root'
    mysql_password = '134679'
    mysql_port = 3306
    db = pymysql.connect(host=mysql_host, port=mysql_port, user=mysql_user, password=mysql_password, db=mysql_db,charset='utf8')
    print('open connect!')
    cursor = db.cursor()
    print('start-insert-data')
    title,article,source,newstypeid = newsinfo[0],newsinfo[1],newsinfo[2],newsinfo[3]
    article = article.replace("'", '"')
    sql = "INSERT INTO news(title,content,source,newstypeid,status,hits)VALUES('%s','%s','%s','%d','%d','%d')"%(title,article,source,newstypeid,0,0)
    #     print("执行sql语句"+sql)
    cursor.execute(sql)
    db.commit()
    db.close()

if __name__ == '__main__':
    issue = ['xw','zc','sj','kj']
    issuen = [1,2,3,4]
    for j in range(len(issue)):
        for i in range(0,1):
            urlList = get_url(issue[j],i)
            for url in urlList:
                newsinfo = get_content(url)
                newsinfo.append(issuen[j])
                insertIntoMysql(newsinfo)