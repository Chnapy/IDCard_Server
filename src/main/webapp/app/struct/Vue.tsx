
import * as React from 'react';

export abstract class Vue<A, B> extends React.Component<A, B> {

	public constructor(props?: any, context?: any) {
		super(props, context);
	}


}