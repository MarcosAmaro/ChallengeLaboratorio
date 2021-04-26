import React, { Component } from 'react';
import { Table, Form, InputGroup, Button } from 'react-bootstrap';
import MedicineTypeOption from './MedicineTypeOption';
import { getMedicines } from '../rest/MedicineService';

class MedicineSearch extends Component {

	constructor(props) {
		super(props);
		this.state = {
			name: null,
			type: null,
			lstMedicine: []
		}

		this.handleSelect = this.handleSelect.bind(this);
		this.search = this.search.bind(this);
	}

	handleSelect(mt) {
		this.setState({ type: mt == undefined ? null : mt.idType });
	}

	search(e) {
		e.preventDefault();
		getMedicines(this.state.name, this.state.type).then(json => {
			this.setState({ lstMedicine: json });
		}
		);
	}

	render() {
		return (
			<div className='d-flex flex-column align-items-center align-middle' style={{ height: 100 }}>
				<h1>Búsqueda de medicinas</h1>
				<Form className="mb-3" onSubmit={this.search}>
					<InputGroup className="mb-3">
						<InputGroup.Prepend>
							<InputGroup.Text>Nombre</InputGroup.Text>
						</InputGroup.Prepend>
						<Form.Control onChange={e => this.setState({ name: e.target.value })} />
					</InputGroup>
					<InputGroup className="mb-3">
						<InputGroup.Prepend>
							<InputGroup.Text>Tipo de medicamento</InputGroup.Text>
						</InputGroup.Prepend>
						<div style={{ width: 5 }}></div>
						<InputGroup.Append className="align-items-right">
							<MedicineTypeOption handleSelect={this.handleSelect} optional={true} />
						</InputGroup.Append>
					</InputGroup>
					<Button type='submit'>Buscar</Button>
				</Form>
				<Table striped bordered hover className="col-6">
					<thead>
						<tr>
							<th>Nombre comercial</th>
							<th>Código</th>
							<th>Droga</th>
							<th>Tipo</th>
						</tr>
					</thead>
					<tbody>
						{this.state.lstMedicine.map((m) =>
							<tr>
								<td>{m.commercialName}</td>
								<td>{m.code}</td>
								<td>{m.drugName}</td>
								<td>{m.typeName}</td>
							</tr>
						)}
					</tbody>
				</Table>
			</div>
		)
	}
}

export default MedicineSearch;
