# 菜鸟朋友圈的相关接口介绍

标签（空格分隔）： 开源项目 Java后台

---

### BaseUrl
* http://localhost:8080/api/

### 注册接口(Post请求)

* 接口   account/register
* 参数
|     参数     | 参数解释 |             是否为空      |
| :------------: | :---: |:-----------------------: |
| username |  姓名 | NO |
| phone    |  手机 | NO |
| password |  密码 | NO |


* 返回的结果形式

```
{
    "code": 1,
    "message": "ok",
    "time": "2017-08-19T23:27:20.558",
    "result": {
        "user": {
            "id": "56e5ef1f-2f99-48e7-a846-70c5a0007073",
            "name": "若兰明月3",
            "phone": "13718989034",
            "password": null,
            "portrait": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg",
            "desc": null,
            "sex": 0
        },
        "account": "13718989034",
        "token": "YmI0YTA4ZWMtY2YwYy00ODI5LTk4N2UtMWI4MjA1ZTJjMTUx"
    }
}
```
### 登录接口(Post请求)
* 接口 account/login
* 参数
|     参数     | 参数解释 |             是否为空      |
| :------------: | :---: |:-----------------------: |
| phone |  姓名 | NO |
| password |  密码 | NO |


* 返回的结果形式
```
{
    "code": 1,
    "message": "ok",
    "time": "2017-08-19T23:30:08.345",
    "result": {
        "user": {
            "id": "50cc22cf-9334-4e40-aa82-e9ca74de84ee",
            "name": "若兰明月",
            "phone": "13718989054",
            "password": null,
            "portrait": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg",
            "desc": null,
            "sex": 0
        },
        "account": "13718989054",
        "token": "Nzk0NTVkOWMtYjg5Mi00OTgxLWExNzItMzA5NDUxNDFjZWMy"
    }
}
```

### 发表动态接口(Post请求)
* 接口   dynamic/publish
* 参数
|     参数     | 参数解释 |             是否为空      |
| :------------: | :---: |:-----------------------: |
| token |  登录成功返回的token 这个要放到请求头里,服务器端根据你的请求头来判断是否已经登录,可以做到单点登录 | NO |
| publishId    |  用户id | NO |
| content |  动态的文字内容 | NO |
|pictures|动态的图片 | YES|


* 返回的结果形式
```
{
    "code": 1,
    "message": "ok",
    "time": "2017-08-19T23:35:07.858",
    "result": {
        "id": "2bc0ecb7-98de-403c-b330-de4a3a0566ce",
        "content": "完成了第一次发布朋友圈的功能",
        "pictures": "[\"http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg\",\"http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg\",\"http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg\",\"http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg\"]",
        "createAt": null,
        "publishId": "56e5ef1f-2f99-48e7-a846-70c5a0007073",
        "user": {
            "id": "56e5ef1f-2f99-48e7-a846-70c5a0007073",
            "name": "若兰明月3",
            "phone": "13718989034",
            "password": null,
            "portrait": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg",
            "desc": null,
            "sex": 0
        }
    }
}
```
### 评论接口(Post请求)
* 接口 dynamic/add/comment
* 参数
|     参数     | 参数解释 |             是否为空      |
| :------------: | :---: |:-----------------------: |
| token |  登录成功返回的token放到请求头里面 | NO |
| dynamicId |  动态id | NO |
| userId |  评论用户id | NO |
| username |  评论用户姓名 | NO |
| content |  评论内容 | NO |
| replayId |  回复评论的评论人id | YES |
| replyNickname |  回复评论的评论人姓名 | YES |


* 返回的结果形式
```
{
    "code": 1,
    "message": "013d893c-2cde-463a-a992-c6d0ca6849e9",
    "time": "2017-08-19T23:37:49.795",
    "result": null
}
```
###点赞接口(Post请求)
* 接口   dynamic/addlike
* 参数
|     参数     | 参数解释 |             是否为空      |
| :------------: | :---: |:-----------------------: |
| token |  登录成功返回的token 这个要放到请求头里,服务器端根据你的请求头来判断是否已经登录,可以做到单点登录 | NO |
| userId    |  用户id | NO |
| dynamicId |  点赞的动态id | NO |



* 返回的数据形式
```
{
    "code": 1,
    "message": "ok",
    "time": "2017-08-19T23:37:49.795",
    "result": null
}
```

### 所有动态接口
* 接口dynamic/list
* 参数
|     参数     | 参数解释 |             是否为空      |
| :------------: | :---: |:-----------------------: |
| token |  登录成功返回的token 这个要放到请求头里,服务器端根据你的请求头来判断是否已经登录,可以做到单点登录 | YES |
| index    |  当前页数 | NO |
| count |  每页的数量 | NO |
|userId| 当前用户id|NO |



* 返回的形式
```
{
    "code": 1,
    "message": "ok",
    "time": "2017-08-20T00:16:25.958",
    "result": [
        {
            "dynamicId": "123c1272-94e2-48bb-8c25-e541359ecd00",
            "dynamicContent": "完成了第一次发布朋友圈的功能",
            "viewCount": "253",
            "likeCount": "2",
            "isLiked": false,
            "commentCount": "0",
            "createData": "2017-08-18T16:18:23",
            "createdDateLabel": null,
            "userId": "50cc22cf-9334-4e40-aa82-e9ca74de84ee",
            "username": "若兰明月",
            "avatar": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg",
            "photos": "null",
            "comments": [],
            "mLikeds": [
                {
                    "userId": "50cc22cf-9334-4e40-aa82-e9ca74de84ee",
                    "avatar": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg"
                },
                {
                    "userId": "56e5ef1f-2f99-48e7-a846-70c5a0007073",
                    "avatar": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg"
                }
            ]
        },
        {
            "dynamicId": "e7894d9a-a742-46f5-9d99-2661214f074d",
            "dynamicContent": "完成了第一次发布朋友圈的功能",
            "viewCount": "101",
            "likeCount": "2",
            "isLiked": true,
            "commentCount": "1",
            "createData": "2017-08-20T00:04:22",
            "createdDateLabel": null,
            "userId": "56e5ef1f-2f99-48e7-a846-70c5a0007073",
            "username": "若兰明月3",
            "avatar": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg",
            "photos": "[\"http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg\",\"http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg\",\"http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg\",\"http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg\"]",
            "comments": [
                {
                    "commentId": "56e5ef1f-2f99-48e7-a846-70c5a0007073",
                    "userId": "56e5ef1f-2f99-48e7-a846-70c5a0007073",
                    "nickname": "若兰明月3",
                    "replyUserid": null,
                    "replyNickname": null,
                    "content": "大侠为何如此威风，何不让小弟快活快活呀"
                }
            ],
            "mLikeds": [
                {
                    "userId": "50cc22cf-9334-4e40-aa82-e9ca74de84ee",
                    "avatar": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg"
                },
                {
                    "userId": "56e5ef1f-2f99-48e7-a846-70c5a0007073",
                    "avatar": "http://loveruolan.oss-cn-shanghai.aliyuncs.com/portrait/201707/6e8ad1e62302f9c446b78c00d51b9cff.jpg"
                }
            ]
        }
    ]
}
```

### 删除评论接口(Post请求)
* 接口dynamic/delete/comment
*  参数
|     参数     | 参数解释 |             是否为空      |
| :------------: | :---: |:-----------------------: |
| token |  登录成功返回的token 这个要放到请求头里,服务器端根据你的请求头来判断是否已经登录,可以做到单点登录 | NO |
| commentId    |  当前的评论的ID | NO |
| dynamicId |  当前评论所在的动态ID | NO |
|userId| 当前用户id|NO |


* 返回的数据形式
```
{
    "code": 1,
    "message": "ok",
    "time": "2017-08-20T00:23:57.340",
    "result":null
}
```
### 取消收藏接口(Post请求)
* 接口dynamic/hidelike
*  参数
|     参数     | 参数解释 |             是否为空      |
| :------------: | :---: |:-----------------------: |
| token |  登录成功返回的token 这个要放到请求头里,服务器端根据你的请求头来判断是否已经登录,可以做到单点登录 | NO |
| dynamicId |  当前评论所在的动态ID | NO |
|userId| 当前用户id|NO |


* 返回的数据形式
```
{
    "code": 1,
    "message": "ok",
    "time": "2017-08-20T00:31:12.359",
    "result": null
}
```
