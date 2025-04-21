-- 创建库
create database if not exists alarm;

-- 切换库
use alarm;

-- 用户表
CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    username    VARCHAR(50)                        NOT NULL COMMENT '用户名',
    email       VARCHAR(100)                       NULL COMMENT '邮箱',
    phone       VARCHAR(20)                        NULL COMMENT '手机号',
    dingtalk_id VARCHAR(100)                       NULL COMMENT '钉钉ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除 1：删除；0：未删除',
    UNIQUE KEY uk_username (username)
) COMMENT '用户' COLLATE = utf8mb4_unicode_ci;

-- 任务表
CREATE TABLE IF NOT EXISTS tasks
(
    id          BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    name        VARCHAR(100)                       NOT NULL COMMENT '任务名称',
    type        ENUM ('TASK_A', 'TASK_B')          NOT NULL COMMENT '任务类型',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除 1：删除；0：未删除',
    INDEX idx_type (type)
) COMMENT '任务' COLLATE = utf8mb4_unicode_ci;

-- 告警类型表
CREATE TABLE IF NOT EXISTS alert_types
(
    id          BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    name        VARCHAR(50)                                               NOT NULL COMMENT '告警类型名称',
    code        ENUM ('FAILURE', 'CANCELLATION', 'INCOMPLETE', 'SUCCESS') NOT NULL COMMENT '告警类型编码',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP                        NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP                        NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   TINYINT  DEFAULT 0                                        NOT NULL COMMENT '是否删除 1：删除；0：未删除',
    UNIQUE KEY uk_code (code)
) COMMENT '告警类型' COLLATE = utf8mb4_unicode_ci;

-- 订阅表
CREATE TABLE IF NOT EXISTS subscriptions
(
    id               BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id          BIGINT                             NOT NULL COMMENT '用户ID',
    task_id          BIGINT                             NOT NULL COMMENT '任务ID',
    alert_type_id    BIGINT                             NOT NULL COMMENT '告警类型ID',
    dingtalk_enabled BOOLEAN  DEFAULT FALSE             NOT NULL COMMENT '钉钉通知是否启用',
    sms_enabled      BOOLEAN  DEFAULT FALSE             NOT NULL COMMENT '短信通知是否启用',
    phone_enabled    BOOLEAN  DEFAULT FALSE             NOT NULL COMMENT '电话通知是否启用',
    create_time      DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time      DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete        TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除 1：删除；0：未删除',
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (alert_type_id) REFERENCES alert_types (id),
    UNIQUE KEY uk_subscription (user_id, task_id, alert_type_id),
    INDEX idx_user_id (user_id),
    INDEX idx_task_id (task_id),
    INDEX idx_alert_type_id (alert_type_id)
) COMMENT '订阅' COLLATE = utf8mb4_unicode_ci;

-- 告警记录表
CREATE TABLE IF NOT EXISTS alert_records
(
    id            BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    user_id       BIGINT                             NOT NULL COMMENT '用户ID',
    task_id       BIGINT                             NOT NULL COMMENT '任务ID',
    alert_type_id BIGINT                             NOT NULL COMMENT '告警类型ID',
    channel       ENUM ('DINGTALK', 'SMS', 'PHONE')  NOT NULL COMMENT '通知渠道',
    content       TEXT                               NULL COMMENT '告警内容',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除 1：删除；0：未删除',
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (alert_type_id) REFERENCES alert_types (id),
    INDEX idx_user_id (user_id),
    INDEX idx_task_id (task_id),
    INDEX idx_alert_type_id (alert_type_id),
    INDEX idx_channel (channel),
    INDEX idx_create_time (create_time)
) COMMENT '告警记录' COLLATE = utf8mb4_unicode_ci;