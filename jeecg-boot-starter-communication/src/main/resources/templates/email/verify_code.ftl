<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>验证码</title>
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
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
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
        .code-box {
            background-color: #f8f9fa;
            border: 2px dashed #667eea;
            border-radius: 8px;
            padding: 20px;
            text-align: center;
            margin: 30px 0;
        }
        .code {
            font-size: 32px;
            font-weight: bold;
            color: #667eea;
            letter-spacing: 8px;
            font-family: 'Courier New', monospace;
        }
        .tips {
            color: #666;
            font-size: 14px;
            line-height: 1.8;
            margin: 20px 0;
        }
        .tips-item {
            margin: 8px 0;
        }
        .warning {
            background-color: #fff3cd;
            border-left: 4px solid #ffc107;
            padding: 15px;
            margin: 20px 0;
            border-radius: 4px;
        }
        .warning-text {
            color: #856404;
            margin: 0;
            font-size: 14px;
        }
        .footer {
            background-color: #f8f9fa;
            padding: 20px;
            text-align: center;
            color: #999;
            font-size: 12px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>${systemName!'JeecgBoot'}</h1>
        </div>
        <div class="content">
            <p style="font-size: 16px; color: #333;">您好！</p>
            <p style="color: #666;">您正在进行身份验证，请使用以下验证码：</p>
            
            <div class="code-box">
                <div class="code">${code}</div>
            </div>
            
            <div class="tips">
                <div class="tips-item">✓ 验证码有效期：<strong>${expireMinutes} 分钟</strong></div>
                <div class="tips-item">✓ 请勿将验证码透露给他人</div>
                <div class="tips-item">✓ 如果这不是您本人的操作，请忽略此邮件</div>
            </div>
            
            <div class="warning">
                <p class="warning-text">
                    <strong>安全提示：</strong>官方不会以任何理由索要您的验证码，请妥善保管！
                </p>
            </div>
        </div>
        <div class="footer">
            <p>此邮件由系统自动发送，请勿直接回复</p>
            <p>© ${.now?string('yyyy')} ${systemName!'JeecgBoot'}. All rights reserved.</p>
        </div>
    </div>
</body>
</html>