import React, { Component } from 'react';
import { Form, InputGroup, Button } from 'react-bootstrap';
import { saveMedicineType } from '../rest/MedicineTypeService';
import MedicineTypeSearch from './MedicineTypeSearch';

class MedicineType extends Component {

	constructor(props) {
		super(props);
		this.state = {
			name: '',
			isActive: true
		}

		this.handleSelect = this.handleSelect.bind(this);
		this.saveMedicineType = this.saveMedicineType.bind(this);
	}

	handleSelect(mt) {
		this.setState({ idType: mt.idType });
	}

	saveMedicineType(e) {
		e.preventDefault();
		saveMedicineType(JSON.stringify(this.state)).then(response => { if (response.ok) return response.json(); throw Error("Error"); })
			.then(data => alert("Tipo de medicamento guardado exitosamente"))
			.catch(e => alert("Error al guardar el tipo de medicamento"));
	}

	render() {
		return (
			<div className="row">
				<div className="col-1"/>
				<div className='col-4  d-flex flex-column align-items-center align-middle'>
					<h1>Alta de tipo</h1>
					<Form className="mb-3" onSubmit={this.saveMedicineType}>
						<InputGroup className="mb-3">
							<InputGroup.Prepend>
								<InputGroup.Text>Nombre</InputGroup.Text>
							</InputGroup.Prepend>
							<Form.Control onChange={e => this.setState({ name: e.target.value })} />
							<div className="col-1"></div>
							<Form.Check type="checkbox" className="align-middle" label="Activo" onChange={ e => this.setState({isActive: e.target.checked})} />
						</InputGroup>
						<Button type='submit'>Guardar</Button>
					</Form>
				</div>
				<MedicineTypeSearch />
				<div className="col-1"/>
			</div>

		)
	}
}

export default MedicineType;