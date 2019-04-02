package com.github.iam20.msg

class HttpResponseMessage {
public static final String MSG_200_OK = '''
HTTP/1.1 200 OK
Content-Type: text/html

<HTML>
	<HEAD><TITLE>200 OK</TITLE></HEAD>
	<BODY>200 OK</BODY>
</HTML>

'''

public static final String MSG_201_CREATED = '''
HTTP/1.1 201 Created
Content-Type: text/html

<HTML>
	<HEAD> <TITLE> 201 Created </TITLE> </HEAD>
	<BODY> 201 Created </BODY>
</HTML>

'''

public static final String MSG_400_BAD_REQUEST = '''
HTTP/1.1 400 BAD Request
Content-Type: text/html

<HTML>
	<HEAD><TITLE>400 BAD Request</TITLE></HEAD>
	<BODY>400 BAD Request</BODY>
</HTML>

'''

public static final String MSG_404_NOT_FOUND = '''
HTTP/1.1 404 NOT Found
Content-Type: text/html

<HTML>
	<HEAD><TITLE>Not Found</TITLE></HEAD>
	<BDOY>Not Found</BODY>
</HTML>

'''

public static final String MSG_500_NOT_FOUND = '''
HTTP/1.1 500 Internal Server Error
Content-Type: text/html

<HTML>
	<HEAD><TITLE>Internal Server Error</TITLE></HEAD>
	<BODY>Internal Server Error</BODY>
</HTML>

'''
}
