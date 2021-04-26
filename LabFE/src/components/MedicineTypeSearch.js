import React, { Component } from 'react';
import { Table, Form, InputGroup, Button } from 'react-bootstrap';
import MedicineTypeOption from './MedicineTypeOption';
import { getMedicineTypesByFilters, updateMedicineTypes } from '../rest/MedicineTypeService';

class MedicineTypeSearch extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: null,
            idType: null,
            lstMedTypes: [],
            lstMedTypesUpd: new Set()
        }

        this.handleSelect = this.handleSelect.bind(this);
        this.handleCheckChange = this.handleCheckChange.bind(this);
        this.updateTypes = this.updateTypes.bind(this);
        this.search = this.search.bind(this);
    }

    handleSelect(mt) {
        this.setState({ idType: mt == undefined ? null : mt.idType });
    }

    handleCheckChange(evt, mt) {
        mt.isActive = evt.target.checked;
        let id = "mt" + mt.idType;
        this.state.lstMedTypesUpd.add(mt);
        this.forceUpdate();
    }

    search(e) {
        e.preventDefault();
        getMedicineTypesByFilters(this.state.name, this.state.idType).then(json => {
            this.setState({ lstMedTypes: json });
        }
        );
    }

    updateTypes(e){
        e.preventDefault;
        let json = JSON.stringify([...this.state.lstMedTypesUpd]);
        updateMedicineTypes(json).then(response => { if (response.ok) return response.json(); throw Error("Error"); })
			.then(data => alert("Tipo de medicamento actualizado exitosamente"))
			.catch(e => alert("Error al actualizar el tipo de medicamento"));
    }

    render() {
        return (
            <div className='col-6 d-flex flex-column align-items-center align-middle' style={{ height: 100 }}>
                <h1>Modificación de tipos</h1>
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
                    <div class="row">

                        <div className="col-2"><Button type='submit'>Buscar</Button></div>
                        <div className="col-1"></div>
                        <div className="col-6">
                            {this.state.lstMedTypesUpd.size > 0 &&
                                <Button type='button' className="btn-success" onClick={this.updateTypes}>Guardar cambios</Button>
                            }

                        </div>
                    </div>

                </Form>
                <Table striped bordered hover className="col-6">
                    <thead>
                        <tr>
                            <th>Activo</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.lstMedTypes.map((m) =>
                            <tr>
                                <td><Form.Check type="checkbox" key={m.idType} checked={m.isActive}
                                    onChange={(e) => { this.handleCheckChange(e, m) }} ></Form.Check></td>
                                <td>{m.name}</td>
                            </tr>
                        )}
                    </tbody>
                </Table>

            </div>
        )
    }
}

export default MedicineTypeSearch;
