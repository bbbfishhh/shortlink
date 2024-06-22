package org.bbbfish.shortlink.admin.test;

public class UserTableShardingTest {
    public static final String SQL = "CREATE TABLE `t_user_%d` (\n" +
            "  `id` bigint NOT NULL,\n" +
            "  `username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
            "  `password` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
            "  `real_name` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
            "  `phone` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
            "  `mail` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
            "  `deletion_time` bigint DEFAULT NULL,\n" +
            "  `create_time` datetime DEFAULT NULL,\n" +
            "  `update_time` datetime DEFAULT NULL,\n" +
            "  `del_flag` tinyint(1) DEFAULT NULL,\n" +
            "  UNIQUE KEY `idx_username` (`id`) USING BTREE\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;";

    public static void main(String[] args) {
        for(int i=0;i<16;i++){
            System.out.printf((SQL) + "%n", i);
        }
    }
}
