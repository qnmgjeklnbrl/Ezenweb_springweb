import React from "react";

class Ex1_Event extends React.Component{


    constructor(props){
        super(props)
        this.state={isToggleOn:true};//메모리 관리
        this.handleClick=this.handleClick.bind(this);
    };
    handleClick(){
        this.setState( prevState => {isToggleOn:!prevState.isToggleOn})
    };
    render(){
        return(
            <button onClick={this.handleClick}>
                {this.state.isToggleOn ? "켜짐" :"꺼짐"}
            </button>
        );
    }

};

export default Ex1_Event;