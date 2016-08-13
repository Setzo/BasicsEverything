interface Address2 {
    city: string;
    street: string;
    streetNumber: number;
    postcode?: any;
}

const data : Address2 = {
    city : 'Gdynia',
    street : 'Swietojanska',
    streetNumber : 32
};

const {
    city: cityAlias,
    street,
    streetNumber,
    postcode = '84240'
} = data;

const employee = {

    address: data,
    position: {
        department: 'DevOps',
        name: 'Dev'
    }
};

const {
    address: {
        city
    },
    position: {
        name : nameAlias
    }
} = employee;


function destParams({
    address: {
        city: cityAlias
    } = {
        city: 'Reda'
    },
    position: {
        name : depName
    }
}) {
    return {
        city: cityAlias,
        depName
    };
}

const func = destParams(employee);

let arr = ['mike', 'bob'];

const [arrDestParam1 = 'default1', arrDestParam2 = 'default2', arrDestParam3 = 'default3', ...restOfParams] = arr || [];


