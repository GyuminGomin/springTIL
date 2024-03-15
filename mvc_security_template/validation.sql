DESC validation_member;

DROP TABLE validation_member;

CREATE TABLE validation_member(
	u_no INT PRIMARY KEY auto_increment,		-- 회원번호
    u_id VARCHAR(50) NOT NULL UNIQUE,			-- 회원아이디
    u_pw VARCHAR(200) NOT NULL,					-- 회원 비밀번호
    u_profile VARCHAR(200) NULL,				-- 회원 프로필 이미지
    u_phone VARCHAR(20) NOT NULL,				-- 회원 전화번호
    u_name VARCHAR(20) NOT NULL,				-- 회원이름
    u_birth VARCHAR(20) NOT NULL,				-- 회원 생년월일
    u_addr VARCHAR(100),						-- 주소
    u_addr_detail VARCHAR(100),					-- 상세주소
	u_addr_post VARCHAR(50), 					-- 우편번호
    u_point INT default 0,						-- 포인트
    u_info char(1) default 'y',					-- 회원정보이용동의 체크여부
    u_date TIMESTAMP NOT NULL default now(),	-- 회원 등록일
    u_visit TIMESTAMP NOT NULL default now(),	-- 최종방문일
    u_withdraw char(1) default 'n'				-- 회원탈퇴요청 여부
);

DESC validation_member;

DROP TABLE validation_member_auth;

-- 권한 table
CREATE TABLE validation_member_auth(
	u_id VARCHAR(50) NOT NULL,					-- 사용자아이디
    u_auth VARCHAR(50) NOT NULL,				-- 사용자 권한
    constraint fk_member_auth FOREIGN KEY(u_id) 
    REFERENCES validation_member(u_id)
);


DESC validation_member;

commit;

SELECT * FROM validation_member;

INSERT INTO validation_member_auth(u_id, u_auth) 
VALUES('test@test.com' , 'ROLE_ADMIN');

INSERT INTO validation_member_auth(u_id,u_auth) 
VALUES('tester@test.com' , 'ROLE_MEMBERSHIP');

SELECT * FROM validation_member AS M NATURAL JOIN validation_member_auth AS A;












