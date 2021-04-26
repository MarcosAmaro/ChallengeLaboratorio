import { ENDPOINT } from '../config';
const MEDTYPE = ENDPOINT + "medType";

export const getTypes = async () => {
    const response = await fetch(MEDTYPE + "/list");
    const json = await response.json();
    return json;
}

export const getMedicineTypesByFilters = async (n,t) => {
    let url = new URL(MEDTYPE + "/listByFilter");
    let params = {};
    
    if (n !== null) params.name = n;
    if (t !== null) params.idType = t;

    url.search = new URLSearchParams(params).toString();

    const response = await fetch(url);
    const json = await response.json();

    return json;
}

export const saveMedicineType = async (json) => {
    const reqOpts = {
        method: 'POST',
        headers: { 'Content-type': 'application/json' },
        body: json
    };
    
    const response = await fetch(MEDTYPE + "/save", reqOpts);
        
    return response;
}

export const updateMedicineTypes = async (json) => {
    const reqOpts = {
        method: 'PUT',
        headers: { 'Content-type': 'application/json' },
        body: json
    };
    
    const response = await fetch(MEDTYPE + "/update", reqOpts);
        
    return response;
}