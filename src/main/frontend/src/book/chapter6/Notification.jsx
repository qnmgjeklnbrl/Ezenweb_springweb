// p.186
// 1. import
import React from 'react'   // 1-1. react import // 필수!!
import Styles from './Notification.css' // 1-2. css import
// 2. 클래스를 이용한 컴포넌트 생성  [ React.Component 에게 상속받기 ]
class Notification extends React.Component {
    // 1. 생성자
    constructor(props) {
        super(props);       // 슈퍼클래스 생성자 호출
        this.state = { };   // 상태관리 변수 [ 비어 있음 ] // 사용안함
    } // end

    // * 생명주기 함수 [ 클래스 컴포넌트 생명주기 함수 사용O - 함수 컴포넌트 생명주기 함수 거의 사용x ]
    componentDidMount(){ console.log( `${this.props.id} 출생 함수 호출. ` ); }
    componentDidUpdate(){ console.log(`${this.props.id} 업데이트 함수 호출. ` ); }
    componentWillUnmount(){ console.log(`${this.props.id} 사망 함수 호출. ` ); }

    // 2. 렌더링 함수
    render(){
        return(
            <div className="wrapper">
                <span className="messageText">
                    { this.props.message }
                    { /* 주석처리 */ }
                </span>
            </div>
        )
    } // render end
} // class end
// 3. 컴포넌트 내보내기
export default Notification;