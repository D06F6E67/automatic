# 自动助手🚀️

掘金自动签到、抽奖、游戏挖矿石，有道笔记自动签到获取空间(暂时去除)。签到抽奖结果发送到微信。

微信消息推送原本想使用公众号，但是公众号推送消息需要企业认证。最后使用了企业微信自定义机器人的方式进行消息推送(企业微信可配置微信接收消息，具体自己百度)。

## 效果

![image.png](assets/effect.png)

## 需要用到的

1. 企业微信
2. 共有IP(云服务器自带)

## 部署

````
├── automatic
│   ├── automatic.jar
│   └── start.sh
├── docker-compose.yml
└── Dockerfile
````

需要docker-compose.yml修改其中的环境变量，如不想使用环境变量可直接修改application.yml文件中的值。

掘金cookie必须配置，微信和钉钉的根据需要自己配置。

目前只使用了微信发送消息，钉钉发送消息需去juejinJob中修改。

## 配置获取

### 掘金cookie获取方法，浏览器掘金登陆后抓任意一个url找到下图请求头中的值即可

![image.png](assets/juejin_getCookie.png)

### 有道笔记获取cookie和上方掘金相同不再介绍

### 企业微信获取企业ID、应用ID和Secret

1. 登陆企业微信自己创建一个
2. 登陆[企业微信管理平台](https://work.weixin.qq.com/wework_admin/frame#index)
3. 我的企业 --> 获取企业ID
4. 应用管理 --> 创建应用 --> 获取应用的ID和Secret <br/>![image.png](assets/weixin_getAppInfo.png)
5. 第4步中最下面 --> 企业可信IP, 将自己服务器的IP配置进去，不然无法退送消息

# CharGPT
1. 账号登陆[获取gpt的key](https://platform.openai.com/account/api-keys)
2. 创建key 然后将这个key写入docker-compose <br/>![img.png](assets/gpt_confoig.png)

# CharGPT(微信)

## 效果 

![img.png](assets/weixin_gpt.png)

## 需要用到的

1. 企业微信
2. CharGPT的key

## 创建机器人和上方步骤一样

1. 将AgentId和Secret写入docker-compose
2. 服务器开放8888端口
3. 应用管理 --> 刚创建的应用 --> 设置API接收 
4. url配置 http://IP:8888/weixin 将token和key写入docker-compose <br/> ![img.png](assets/weixin_setAPI.png)

# CharGPT(钉钉)

## 效果

![img.png](assets/dingtalk_gpt.png)

## 需要用到的

1. 钉钉
2. CharGPT的key

## 创建钉钉机器人
1. 登陆[钉钉开发平台](https://open-dev.dingtalk.com/?spm=dd_developers.homepage.0.0.205a4a97lQMxqS#/)
2. 应用开发 --> 企业内部开发 --> 创建应用 <br/> ![img.png](assets/dingtalk_create.png)
3. 获取应用的信息，将两个配置写入 docker-compose中 <br/> ![img.png](assets/dingtalk_info.png)
4. 设置消息退送地址，自己的服务器公网IP:8888/dingtalk  然后点击发布 <br/> ![img.png](assets/dinktalk_config.png)