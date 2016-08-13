
function wowify(...strings: string[]) {

    strings.forEach((item, index) => {
        strings[index] = `${item} wow.`;
    });
    return strings.join(',');
}

function mehify(...strings: string[]) {

    strings.forEach((item, index) => {
        strings[index] = `${item} meh.`;
    });
    return strings.join(',');
}

export {wowify as superWowify, mehify};