import React , { useState , useEffect } from 'react'
import axios from 'axios'
import Pagination from 'react-js-pagination' // npm i react-js-pagination 설치

export default function BoardList(){
    const [ pageInfo , setPageInfo ] = useState({ bcno : 0 , page : 1 , key : "" , keyword:""  }) // 1. 요청 정보 객체 state
    const [ pageDto , setPageDto ] = useState( { list : [] } )                                // 1. 게시물 리스트 state
    const [ bcategory , setBcategoryList ] = useState( [ ] )                                      // 1. 카테고리 리스트
                                                    // [ ] : array/list     {  } : object/dto
    // ------------------------------  1. 게시물  -------------------------------------- //
    function getboardlist( ){ // 2. server : pageInfo 요청 => pageDto 응답 [ 실행조건 : 1. 렌더링될때 2.검색할때 3.카테고리선택 4.페이징 선택  ---> 일반 함수화 ]
        axios   .post( "/board/boardlist" ,  pageInfo )
                .then( res => {  console.log( res.data );  setPageDto( res.data );  } ).catch( err => { console.log( err ); } )
    }
    useEffect( getboardlist , [ pageInfo ] )  // 3. 렌더링 될때 그리고 *** pageInfo 변경될때 마다
    // -------------------------------------------------------------------------------- //
    // ------------------------------  2.카테고리 -------------------------------------- //
    function getBcategory(){ // 카테고리 리스트 가져오기
        axios.get("/board/bcategorylist")
            .then( res => { setBcategoryList( res.data ); } ) .catch( err => { console.log( err); } )
    }
    useEffect( getBcategory , [ ] ) // mount , unmount

    // 카테고리 버튼을 선택했을때
    const onCategory = ( bcno ) =>{ setPageInfo( { bcno : bcno , page : 1 , key : "" , keyword:""  } )  }
    // ------------------------------ --------------------------------------------------- //

    // ------------------------------  3.페이징  -------------------------------------- //
    const onPage = ( page ) =>{
        setPageInfo(
            {   bcno : pageInfo.bcno ,          // 기존 카테고리
                page : page ,
                key : pageInfo.key ,            // 기존 검색 필드명
                keyword : pageInfo.keyword  }   // 기존 검색할 단어
        )
    }
    // ------------------------------ --------------------------------------------------- //
    // ------------------------------  4. 검색   -------------------------------------- //
    const onSearch = () => {
        setPageInfo(
            {   bcno : pageInfo.bcno ,  // 카테고리 번호 [ 기존 그대로 : pageInfo.bcno ]
                page : 1 ,              // 검색시 첫페이지부터 보여주기 [ 1 ]
                key : document.querySelector('.key').value ,    // 검색할 필드명
                keyword: document.querySelector('.keyword').value  } // 검색할 단어
        )
    }

    const loadView = ( bno ) => {
        window.location.href = '/board/view/'+bno
    }

    return (
        <div>
            <a href="/board/write">글쓰기[로그인했을때만표시]</a>
            <h1> 글 목록 페이지 </h1>

            <div className="bcategorybox">

                <button type="button" onClick = { ()=> onCategory( 0 ) }> 전체보기 </button>

                {
                    bcategory.map( (c) => {
                        return (
                            <button type="button" onClick = { ()=> onCategory( c.bcno ) }>{c.bcname}</button>
                        )
                    })
                }

            </div>

            <table className="btable">
                {
                    pageDto.list.map( ( b ) => {
                        return (
                            <tr>
                                <td> { b.bno } </td>
                                <td onClick={ ( ) => loadView( b.bno ) }> { b.btitle } </td>
                                <td> { b.memail } </td>
                                <td> { b.bdate } </td>
                                <td> { b.bview } </td>
                            </tr>
                        )
                    } )
                }
            </table>

            <Pagination
                activePage={ pageInfo.page  }
                itemsCountPerPage = { 3 }
                totalItemsCount = { pageDto.totalBoards }
                pageRangeDisplayed = { 5 }
                onChange={ onPage }
            />

            <div className="searchBox">
                <select className="key">
                    <option value="btitle">제목</option>
                    <option value="bcontent">내용</option>
                </select>
                <input type="text" className="keyword" />
                <button type="button" onClick={ onSearch }> 검색 </button>
            </div>


        </div>
    );
}

/*
훅
	1. useState	: 컴포넌트내 메모리
		const [ 변수명 , set데이터변경함수 ] = useState( 초기값 )
	2. useEffect	: 생명주기에 따른 이벤트 실행
		1. useEffect( 이벤트함수  )		: mount , update , unmount
		2. useEffect( 이벤트함수 , []  )		: mount  , unmount
		3. useEffect( 이벤트함수 , [의존성배열]  )	: mount  , unmount , 의존성배열이 업데이트 있을때
*/

/*
리액트 페이징 API
	1. 설치
		npm i react-js-pagination
	2. 컴포넌트 가져오기
		import Pagination from 'react-js-pagination'
	3. 컴포넌트 사용
		<Pagination
            activePage={ 현재페이지 번호  }
            itemsCountPerPage = { 페이지당 표시할 게시물수 }
            totalItemsCount = { 게시물 총 개수 }
            pageRangeDisplayed = {  표시할 페이징버튼 최대 개수 }
            onChange={ 버튼 클릭할때마다 이벤트[ 자동으로 인수로 페이징번호 전달 ] }
        />
		------ 예시
        <Pagination
            activePage={ pageInfo.page  }
            itemsCountPerPage = { 3 }
            totalItemsCount = { pageDto.totalBoards }
            pageRangeDisplayed = { 5 }
            onChange={ onPage }
        />
*/