import { ENDPOINT } from '../config';
const MEDICINE = ENDPOINT + "medicine";

export const getMedicines = async (n,t) => {
    let url = new URL(MEDICINE + "/list");
    let params = {};
    
    if (n !== null) params.name = n;
    if (t !== null) params.type = t;

    url.search = new URLSearchParams(params).toString();

    const response = await fetch(url);
    const json = await response.json();

    return json;
}

export const saveMedicine = async (json) => {
    const reqOpts = {
        method: 'POST',
        headers: { 'Content-type': 'application/json' },
        body: json
    };
    
    const response = await fetch(MEDICINE + "/saveMed", reqOpts);
        
    return response;
}