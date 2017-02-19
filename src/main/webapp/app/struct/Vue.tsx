
import * as React from 'react';
//import * as ReactDOM from 'react-dom';	//TODO: typings react-dom

export abstract class Vue extends React.Component<any, any> {
	
	public constructor(props?: any, context?: any){
      super(props, context);
    }
	
	public applyVue(): void {
//		React.DOM.render(
//		<div> test </div>
//		);
	}

}