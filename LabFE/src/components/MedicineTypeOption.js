import React, { Component } from 'react';
import { Form } from 'react-bootstrap';
import { getTypes } from '../rest/MedicineTypeService'

class MedicineTypeOption extends Component {
    constructor(props) {
        super(props);
        this.state = {
            medTypes: []
        }

        this.handleSelect = this.handleSelect.bind(this);
    }

    handleSelect(e){
        this.props.handleSelect(this.state.medTypes.find(mt => {return mt.idType == e.target.value;}));
    }

    componentDidMount() {
        getTypes().then(json => {
                this.setState({medTypes:json}); 
                if (this.state.medTypes.length > 0 && !this.props.optional){
                    this.props.handleSelect(this.state.medTypes[0]);
                }
            }
        );
    }

    render() {
        return (
            <Form.Control as="select" className="align-right" onChange={ this.handleSelect }>
                {this.props.optional && 
                    <option value="" key="-1"></option>
                }
                {this.state.medTypes.map((mt) =>
                    <option value={mt.idType} key={mt.idType}> {mt.name} </option>
                )}
            </Form.Control>
        )
    }
}

export default MedicineTypeOption;