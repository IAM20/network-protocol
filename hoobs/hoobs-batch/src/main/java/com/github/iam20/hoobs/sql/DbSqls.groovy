package com.github.iam20.hoobs.sql

class DbSqls {
public static final String GET_MAC_ADDRESSES = '''
SELECT * FROM mac_and_vendor
'''

public static final String INSERT_MAC_ADDRESSES = '''
INSERT INTO mac_and_vendor
VALUE ( :macAddr , :vendor );
'''
}
