// p.255
import React from 'react'
class ConfirmButton extends React.Component {
    constructor(props) { // 1. 생성자
        super(props);
        this.state = { isConfirmed : false }    // 리액트 컴포넌트의 데이터 변수 [ 재 랜더링 - 업데이트 ]
        this.handleConfirm = this.handleConfirm.bind(this);
    }
    handleConfirm() { // 3. 이벤트 함수 정의
        // this.setState(  () => ( {  }) );
        this.setState(  ( prevState ) =>
            ( { isConfirmed : !prevState.isConfirmed })
        );
    }
    render() {  // 2. 렌더링 함수
        return (
            <button onClick={ this.handleConfirm }
            disabled = { this.state.isConfirmed} >
                { this.state.isConfirmed ? "확인됨" : "확인하기"}
            </button>
        );
    }
}
export default ConfirmButton;
