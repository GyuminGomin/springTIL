-- message.sql
CREATE TABLE IF NOT EXISTS tbl_user (
	uno INT PRIMARY KEY auto_increment,
	uid VARCHAR(50) NOT NULL UNIQUE,
	upw VARCHAR(50) NOT NULL,
	uname VARCHAR(50) NOT NULL,
	upoint INT NOT NULL DEFAULT 0
);

-- message
CREATE TABLE IF NOT EXISTS tbl_message (
	mno INT PRIMARY KEY auto_increment,			-- 메세지 번호
	targetid VARCHAR(50) NOT NULL,				-- 수신자 아이디
	sender VARCHAR(50) NOT NULL,				-- 발신자 아이디
	message TEXT NOT NULL,						-- 발신 메시지
	openddate TIMESTAMP NULL,					-- 수신 확인 시간
	senddate TIMESTAMP NOT NULL DEFAULT now(),	-- 발신시간
	FOREIGN KEY(targetid) REFERENCES tbl_user(uid),
	FOREIGN KEY(sender) REFERENCES tbl_user(uid)
);

alter table tbl_message CHANGE opendate opendate TIMESTAMP NULL;

INSERT INTO tbl_user(uid, upw, uname) VALUES
('id001', 'pw001', 'IRON MAN'),
('id002', 'pw002', 'THOR'),
('id003', 'pw003', 'DR.strange');

SELECT * FROM tbl_user;

SELECT * FROM tbl_message;

/*
   1 id001 pw001 IRON MAN        0
   2 id002 pw002 THOR            0
   3 id003 pw003 DR.strange      0
*/