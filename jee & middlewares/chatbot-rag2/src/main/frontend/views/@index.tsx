import "../themes/my-theme/styles.css"
import "bootstrap/dist/css/bootstrap.min.css"

import {TextField} from "@vaadin/react-components";
import {style} from "@vaadin/vaadin-lumo-styles";
import React, {useState} from "react";
import {ChatAiServirce} from "Frontend/generated/endpoints";
import Markdown from "react-markdown";
export default function HomeView() {
    const [question,setQuestion]=useState<string>("");
    const [response,setResponse]=useState<string|undefined>("");

       function send(){
        console.log('ddddddddd')


        ChatAiServirce.regChat(question).then(e=> {


            setResponse(e);

        });

    }

    // @ts-ignore
    return (

    <div className="p-m  justify-center items-center" style={{width:'100%'}}  >
        <h3> Chat </h3>

        <div>
            <TextField style={{width:'80%'}} onChange={(e=>setQuestion(e.target.value))}></TextField>
            <button className="btn btn-primary" onClick={send}>Send</button>
        </div>
        <Markdown>
            {response}
        </Markdown>


    </div>
  )
}

