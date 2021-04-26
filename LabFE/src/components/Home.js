import React, { Component } from 'react';
import { Tab, Tabs } from 'react-bootstrap';
import Medicine from './Medicine';
import MedicineSearch from './MedicineSearch';
import MedicineType from './MedicineType';

export default function Home() {
    return (
        <Tabs defaultActiveKey="MedSave" id="uncontrolled-tab-example">
            <Tab eventKey="MedSave" title="Medicina - Alta">
                <Medicine />
            </Tab>
            <Tab eventKey="MedSearch" title="Medicina - Búsqueda">
                <MedicineSearch />
            </Tab>
            <Tab eventKey="MedType" title="Tipo de medicina">
                <MedicineType />
            </Tab>
        </Tabs>
    );
};