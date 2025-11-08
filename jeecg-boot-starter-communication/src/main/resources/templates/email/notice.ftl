<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title!'系统通知'}</title>
    <style>
        body {
            font-family: 'Microsoft YaHei', Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        .header {
            background: linear-gradient(135deg, #1890ff 0%, #096dd9 100%);
            color: #ffffff;
            padding: 30px;
            text-align: center;
        }
        .header h1 {
            margin: 0;
            font-size: 24px;
        }
        .content {
            padding: 40px 30px;
        }
        .title {
            font-size: 20px;
            color: #333;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid #1890ff;
        }
        .message-box {
            background-color: #f8f9fa;
            border-left: 4px solid #1890ff;
            padding: 20px;
            margin: 20px 0;
            border-radius: 4px;
            line-height: 1.8;
            color: #333;
        }
        .footer {
            background-color: #f8f9fa;
            padding: 20px;
            text-align: center;
            color: #999;
            font-size: 12px;
        }
        .time {
            color: #999;
            font-size: 14px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>${systemName!'JeecgBoot'}</h1>
        </div>
        <div class="content">
            <div class="title">${title!'系统通知'}</div>
            
            <div class="message-box">
                ${message}
            </div>
            
            <div class="time">
                通知时间：${.now?string('yyyy-MM-dd HH:mm:ss')}
            </div>
        </div>
        <div class="footer">
            <p>此邮件由系统自动发送，请勿直接回复</p>
            <p>© ${.now?string('yyyy')} ${systemName!'JeecgBoot'}. All rights reserved.</p>
        </div>
    </div>
</body>
</html>