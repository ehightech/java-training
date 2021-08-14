DROP PROCEDURE IF EXISTS CreateOrderTable;
DELIMITER //
CREATE PROCEDURE CreateOrderTable()
BEGIN
    DECLARE `@createSql` VARCHAR (2560);
    DECLARE `@idx` int (11);
    set `@idx` = 0;
    while (`@idx` < 16) do
		    SET @createSql = CONCAT("CREATE TABLE order_info", `@idx`, " (
			`id` bigint NOT NULL COMMENT '订单id',
			`member_id` bigint NOT NULL COMMENT '会员id',
			`order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
			`member_username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户帐号',
			`create_time` datetime NULL DEFAULT NULL COMMENT '提交时间',
			PRIMARY KEY (`id`) USING BTREE
			) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;");
    prepare stmt from @createSql;
    execute stmt;
    set `@idx` = `@idx` + 1;
    end while;
END //
DELIMITER ;

CALL CreateOrderTable();