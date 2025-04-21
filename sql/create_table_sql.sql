-- 创建库
create database if not exists alarm;

-- 切换库
use alarm;

-- 用户表
CREATE TABLE IF NOT EXISTS user
(
    id         BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    username   VARCHAR(50)                        NOT NULL COMMENT '用户名',
    message    VARCHAR(100)                       NULL COMMENT '短信',
    phone      VARCHAR(20)                        NULL COMMENT '手机号',
    dingtalkId VARCHAR(100)                       NULL COMMENT '钉钉ID',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除 1：删除；0：未删除',
    UNIQUE KEY uk_username (username)
) COMMENT '用户' COLLATE = utf8mb4_unicode_ci;

-- 任务表
CREATE TABLE IF NOT EXISTS task
(
    id         BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    name       VARCHAR(100)                       NOT NULL COMMENT '任务名称',
    type       VARCHAR(100)                       NOT NULL COMMENT '任务类型',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除 1：删除；0：未删除',
    INDEX idx_type (type)
) COMMENT '任务' COLLATE = utf8mb4_unicode_ci;

-- 告警类型表
CREATE TABLE IF NOT EXISTS alert_type
(
    id         BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    name       VARCHAR(50)                        NOT NULL COMMENT '告警类型名称',
    code       VARCHAR(100)                       NOT NULL COMMENT '告警类型编码',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除 1：删除；0：未删除',
    UNIQUE KEY uk_code (code)
) COMMENT '告警类型' COLLATE = utf8mb4_unicode_ci;

-- 订阅表
CREATE TABLE IF NOT EXISTS subscription
(
    id              BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    userId          BIGINT                             NOT NULL COMMENT '用户ID',
    taskId          BIGINT                             NOT NULL COMMENT '任务ID',
    alertTypeId     BIGINT                             NOT NULL COMMENT '告警类型ID',
    dingtalkEnabled BOOLEAN  DEFAULT FALSE             NOT NULL COMMENT '钉钉通知是否启用',
    messageEnabled  BOOLEAN  DEFAULT FALSE             NOT NULL COMMENT '短信通知是否启用',
    phoneEnabled    BOOLEAN  DEFAULT FALSE             NOT NULL COMMENT '电话通知是否启用',
    createTime      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete        tinyint  default 0                 not null comment '是否删除 1：删除；0：未删除',
    FOREIGN KEY (userId) REFERENCES user (id),
    FOREIGN KEY (taskId) REFERENCES task (id),
    FOREIGN KEY (alertTypeId) REFERENCES alert_type (id),
    UNIQUE KEY uk_subscription (userId, taskId, alertTypeId),
    INDEX idx_user_id (userId),
    INDEX idx_task_id (taskId),
    INDEX idx_alert_type_id (alertTypeId)
) COMMENT '订阅' COLLATE = utf8mb4_unicode_ci;

-- 告警记录表
CREATE TABLE IF NOT EXISTS alert_record
(
    id          BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    userId      BIGINT                             NOT NULL COMMENT '用户ID',
    taskId      BIGINT                             NOT NULL COMMENT '任务ID',
    alertTypeId BIGINT                             NOT NULL COMMENT '告警类型ID',
    channel     ENUM ('DINGTALK', 'SMS', 'PHONE')  NOT NULL COMMENT '通知渠道',
    content     TEXT                               NULL COMMENT '告警内容',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除 1：删除；0：未删除',
    FOREIGN KEY (userId) REFERENCES user (id),
    FOREIGN KEY (taskId) REFERENCES task (id),
    FOREIGN KEY (alertTypeId) REFERENCES alert_type (id),
    INDEX idx_user_id (userId),
    INDEX idx_task_id (taskId),
    INDEX idx_alert_type_id (alertTypeId),
    INDEX idx_channel (channel),
    INDEX idx_create_time (createTime)
) COMMENT '告警记录' COLLATE = utf8mb4_unicode_ci;


INSERT INTO user (username, message, phone, dingtalkId, isDelete)
VALUES ('用户1', '13800000001', '13800000001', 'dingding_id_001', 0),
       ('用户2', '13900000002', '13900000002', 'dingding_id_002', 0);


INSERT INTO task (name, type, isDelete)
VALUES ('测试任务A', 'TASK_A', 0),
       ('测试任务B', 'TASK_B', 0);



INSERT INTO alert_type (name, code, isDelete)
VALUES ('成功告警', 'SUCCESS', 0),
       ('失败告警', 'FAILURE', 0),
       ('取消告警', 'CANCELLATION', 0),
       ('未完成告警', 'INCOMPLETE', 0);