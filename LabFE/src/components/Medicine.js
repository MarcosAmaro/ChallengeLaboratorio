import React, { Component } from 'react';
import { Form, Button, InputGroup } from 'react-bootstrap';
import MedicineTypeOption from './MedicineTypeOption';
import {saveMedicine} from '../rest/MedicineService';

class Medicine extends Component {

	constructor(props) {
		super(props);
		this.state = {
			idMedicine: '',
			code: '',
			commercialName: '',
			drugName: '',
			idType: ''
		}

		this.handleSelect = this.handleSelect.bind(this);
		this.saveMedicine = this.saveMedicine.bind(this);
	}

	handleSelect(mt) {
		this.setState({ idType: mt.idType });
	}

	saveMedicine(e) {
		e.preventDefault();
		saveMedicine(JSON.stringify(this.state)).then(response => {if (response.ok) return response.json(); throw Error("Error");})
        .then(data => alert("Medicamento guardado exitosamente"))
        .catch(e => alert("Error al guardar el medicamento"));
	}

	render() {
		return (
			<div className='d-flex flex-column align-items-center align-middle' style={{ height: 100 }}>
				<h1>Alta de medicamento</h1>
				<Form className="mb-3" onSubmit={this.saveMedicine}>
					<InputGroup className="mb-3">
						<InputGroup.Prepend>
							<InputGroup.Text>Nombre comercial</InputGroup.Text>
						</InputGroup.Prepend>
						<Form.Control onChange={e => this.setState({ commercialName: e.target.value })} />
					</InputGroup>
					<InputGroup className="mb-3">
						<InputGroup.Prepend>
							<InputGroup.Text>Código</InputGroup.Text>
						</InputGroup.Prepend>
						<Form.Control onChange={e => this.setState({ code: e.target.value })} />
					</InputGroup>
					<InputGroup className="mb-3">
						<InputGroup.Prepend>
							<InputGroup.Text>Tipo de medicamento</InputGroup.Text>
						</InputGroup.Prepend>
						<div style={{ width: 5 }}></div>
						<InputGroup.Append className="align-items-right">
							<MedicineTypeOption handleSelect={this.handleSelect} optional={false}/>
						</InputGroup.Append>
					</InputGroup>
					<InputGroup className="mb-3">
						<InputGroup.Prepend>
							<InputGroup.Text>Nombre de la droga</InputGroup.Text>
						</InputGroup.Prepend>
						<Form.Control onChange={e => this.setState({ drugName: e.target.value })} />
					</InputGroup>
					
					<Button type='submit'>Guardar</Button>
				</Form>
			</div>
		)
	}
}

export default Medicine;