package com.Ezenweb.service;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service //해당 클래스가 Service 임을 명시
public class MemberService implements UserDetailsService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired // 스프링 컨테이너 [ 메모리 ] 위임
    private HttpServletRequest request ;            // 요청 객체

    @Override
    public UserDetails loadUserByUsername(String memail ) throws UsernameNotFoundException {
        // 1. 입력받은 아이디 [ memail ] 로 엔티티 찾기
        MemberEntity memberEntity = memberRepository.findByMemail( memail )
                .orElseThrow( ()-> new UsernameNotFoundException("사용자가 존재하지 않습니다,") ); // .orElseThrow : 검색 결과가 없으면 화살표함수[람다식]를 이용한
        // 2. 검증된 토큰 생성
        Set<GrantedAuthority>  authorities = new HashSet<>();
        authorities.add( new SimpleGrantedAuthority("일반회원") ); // 토큰정보에 일반회원 내용 넣기
        // 3.
        MemberDto memberDto = memberEntity.toDto(); // 엔티티 --> Dto
        memberDto.setAuthorities( authorities );       // dto --> 토큰 추가
        return memberDto; // Dto 반환 [ MemberDto는 UserDetails 의 구현체 ]
            // 구현체 : 해당 인터페이스의 추상메소드[선언만]를 구현해준 클래스의 객체
    }


    public  MemberEntity getEntity(){

        Object object = request.getSession().getAttribute("loginMno");
        if( object == null ){return null;}
        int mno = (Integer)object;
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if( !optional.isPresent() ){ return null;}
        return optional.get();


    }
    @Transactional
    public int setmember(MemberDto memberDto ){
        // 암호화 : 해시함수 사용하는 암호화 기법중 하나 [ BCrypt ]
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setMpassword( passwordEncoder.encode( memberDto.getPassword() ) );

        // 1. DAO 처리 [ insert ]
        MemberEntity entity = memberRepository.save( memberDto.toEntity() );
            // memberRepository.save( 엔티티 객체 ) : 해당 엔티티 객체가 insert 생성된 엔티티객체 반환
        entity.setMrol("user");
         // 2. 결과 반환 [ 생성된 엔티티의 pk값 반환 ]
        return entity.getMno();
    }

    // // 2. 로그인     [시큐리티 사용시 필요 없음]
    // @Transactional
    // public int getmember(MemberDto memberDto ){
    //     // 1. Dao 처리 [ select ]
    //     // 1. 모든 엔티티=레코드 호출 [ select * from member ]
    //     List<MemberEntity> entityList = memberRepository.findAll();
    //     // 2. 입력받은 데이터와 일치값 찾기
    //     for( MemberEntity entity : entityList ){ // 리스트 반복
    //         if( entity.getMemail().equals(memberDto.getMemail())){ // 엔티티=레코드 의 이메일 과 입력받은 이메일
    //             if( entity.getMpassword().equals(memberDto.getMpassword())){ // 엔티티=레코드 의 패스워드 와 입력받은 패스워드
    //                 // 세션 부여 [ 로그인 성공시 'loginMno'이름으로 회원번호 세션 저장  ]
    //                 request.getSession().setAttribute("loginMno" , entity.getMno() );
    //                 return 1;// 로그인 성공했다.
    //             }else{
    //                 return 2; // 패스워드 틀림
    //             }
    //         }
    //     }
    //     return 0; // 아이디가 틀림
    // }
    // 3. 비밀번호찾기
    @Transactional
    public String getpassword( String memail ){
        // 1. 모든 레코드/엔티티 꺼내온다.
        List<MemberEntity> entityList
                = memberRepository.findAll();
        // 2. 리스트에 찾기
        for( MemberEntity entity : entityList ){ // 리스트 반복
            if( entity.getMemail().equals( memail) ){
                return entity.getMpassword();
            }
        }
        return null;
    }
    // 4. 회원탈퇴
    @Transactional
    public int setdelete( String mpassword ){
        // 1. 로그인된 회원의 엔티티 필요!!
        // 1. 세션 호출
        Object object =  request.getSession().getAttribute("loginMno");
        // 2. 세션 확인
        if( object != null ){ // 만약에 세션이 null 이 아니면 로그인 됨
            int mno = (Integer) object; // 형변환 [ object --> int ]
            // 3. 세션에 있는 회원번호[PK] 로 리포지토리 찾기 [ findById : select * from member where mno = ? ]
            Optional< MemberEntity > optional =  memberRepository.findById(mno);
            if( optional.isPresent() ){ // optional객체내 엔티티 존재 여부 판단
                // Optional 클래스 : null 관련 메소드 제공
                // 4.Optional객체에서 데이터[엔티티] 빼오기
                MemberEntity entity = optional.get();
                // 5. 탈퇴 [ delete : delete from member where mno = ? ]
                memberRepository.delete( entity );
                // 6. 세션 [ 세션삭제 = 로그아웃 ]
                request.getSession().setAttribute("loginMno" , null);
                return 1;
            }
        }
        return 0; // [ 만약에 세션이 null 이면 반환 o 혹은 select 실패시   ]
    }

    // 5. 회원 수정
    @Transactional // 데이터 수정[update]시 필수 ~~
    public int setupdate( String mpassword ){
        // 1. 세션 호출
        Object object = request.getSession().getAttribute("loginMno");
        // 2. 세션 존재여부 판단
        if( object != null ){
            int mno = (Integer)object;
            // 3. pk값을 가지고 엔티티[레코드] 검색
            Optional<MemberEntity> optional
                    =  memberRepository.findById( mno );
            // 4. 검색된 결과 여부 판단
            if( optional.isPresent() ){ // 엔티티가 존재하면
                MemberEntity entity = optional.get();
                // 5. 찾은 엔티티[레코드]의 필드값 변경 [ update member set 필드명 = 값  where 필드명 = 값 ]
                entity.setMpassword( mpassword );
                return  1 ;
            }
        }
        return 0;
    }
      // 6. 로그인 여부 판단 메소드 [ http 세션 ]
//    public int getloginMno(){
//        // 1. 세션 호출
//        Object object  = request.getSession().getAttribute("loginMno");
//        // 2. 세션 여부 판단
//        if( object != null ){ return (Integer) object; }
//        else{ return 0; }
//    }
    // 6. 로그인 여부 판단 메소드 [ principal 세션 ]
    public String getloginMno(){
        //1. 인증된 토큰 확인      [ SecurityContextHolder 인증된 토큰 보관소 ---> UserDetails(MemberDto) ]
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //2. 인증된 토큰 내용 확인
        Object principal = authentication.getPrincipal(); // Principal:접근주체 [ UserDetails(MemberDto) ]
        System.out.println("토큰 내용확인 : " + principal );
        // 3. 토큰 내용에 따른 제어
        if( principal.equals("anonymousUser") ){ // anonymousUser 이면 로그인전
            return null;
        }else{ // anonymousUser 아니면 로그인후
            MemberDto memberDto = (MemberDto) principal;
            return memberDto.getMemail();
        }
    }
//    // 7. 로그아웃 [ http 세션 ]
//    public void logout(){
//        // 기본 세션명의 세션데이터를 null
//        request.getSession().setAttribute("loginMno" , null );
//    }

    // 8. 회원목록 서비스

    public List<MemberDto> list (){

        List<MemberEntity> list = memberRepository.findAll();
        List<MemberDto> dtolist = new ArrayList<>();

        for(MemberEntity entity: list){
            dtolist.add(entity.toDto());

        }
        return dtolist;
    }

    //9. 메일 전송 서비스
    public String getauth (String toemail){
        String auth = "";
        String html = "<html><body><h1> EzenWeb인증코드입니다.</h1>";
        Random random = new Random();
        for(int i=0;i<6;i++){
            char ranchar = (char) (random.nextInt(26)+97);
            // char ranchar2 = random.nextInt(10)+48;
            auth += ranchar;

        }

        html += "<div>인증코드 : "+auth+"</div></body></html>";
        memailsend(toemail, "ezenWeb 인증코드 입니다.", html);
        return auth;


    }
    public void memailsend(String toemail, String title, String content){
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setFrom("rns0721@naver.com", "장군");
            mimeMessageHelper.setTo(toemail);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content.toString(), true);
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println("mail보내기 오류"+e);
        }
    }



}
