const functions = require('firebase-functions');

const json = [
    {
        "equipmentType": "3D printing",
        "equipmentName": "FDM",
        "price":  "60 UAH per hour",
        "workingArea": "223x223x205 мм, 250x200x200 мм, 300x200x440 мм",
        "description": "-",
        "photoUrl": "https://monkeyecho.com/wp-content/uploads/alfawise-u20-pro-auto-leveling-creative-fdm-3d-printer.jpg"
    },
    {
        "equipmentType": "3D printing",
        "equipmentName": "SLA",
        "price":  "250 UAH per hour",
        "workingArea": "145x145x175 мм",
        "description": "< 25 micron",
        "photoUrl": "https://images.ru.prom.st/382653577_formlabs-form-2.jpg"
    },
    {
        "equipmentType": "3D printing",
        "equipmentName": "SLS",
        "price":  "350 UAH per hour",
        "workingArea": "100x100х100 мм",
        "description": "-",
        "photoUrl": "https://www.prodways.com/en/wp-content/uploads/sites/2/2016/05/3D-printer-plastic-laser-sintering-professionals-industry-2018-1.png"
    },
        {
        "equipmentType": "3D printing",
        "equipmentName": "DLP Basic",
        "price":  "200 UAH per hour",
        "workingArea": "155x85x150 мм",
        "description": "< 25 micron",
        "photoUrl": "https://cdn03.plentymarkets.com/ioseuwg7moqp/item/images/22879/full/Anycubic-Photon-DLP-3D-Drucker-Photon-22879.jpg"
    },
    {
        "equipmentType": "Laser cutting",
        "equipmentName": "Industrial CO2 laser",
        "price":  "350 UAH per hour",
        "workingArea": "900x600 мм",
        "description": "110W",
        "photoUrl": "https://aronis.com.ua/sites/default/files/img-equipment/LS900Fibre-gris.jpg?itok=RkU6Qxi5"
    },
    {
        "equipmentType": "Laser cutting",
        "equipmentName": "3D Laser Glowforge",
        "price":  "300 UAH per hour",
        "workingArea": "200x200 мм",
        "description": "-",
        "photoUrl": "https://images-na.ssl-images-amazon.com/images/I/61bsOPw3BZL._SX466_.jpg"
    },
        {
        "equipmentType": "Working with textiles",
        "equipmentName": "Thermal press",
        "price":  "250 UAH per hour",
        "workingArea": "300x300 мм",
        "description": "-",
        "photoUrl": "https://www.rdmkit.ru/assets/images/termopressu/ploskijb.jpg"
    },
    {
        "equipmentType": "Working with textiles",
        "equipmentName": "Automatic sewing machine",
        "price":  "250 UAH per hour",
        "workingArea": "200x200 мм",
        "description": "-",
        "photoUrl": "https://images-na.ssl-images-amazon.com/images/I/716Zv8lKxfL._SX425_.jpg"
    },
    {
        "equipmentType": "Working with textiles",
        "equipmentName": "Plotter",
        "price":  "250 UAH per hour",
        "workingArea": "-",
        "description": "-",
        "photoUrl": "https://www.c3controls.com/wp-content/uploads/2017/08/WTB2-VP600-Plotter-600x350-450x263.jpg"
    },
        {
        "equipmentType": "3D scan",
        "equipmentName": "PRO",
        "price":  "350 UAH per hour",
        "workingArea": "60x500 мм",
        "description": "60 micron",
        "photoUrl": "https://images-na.ssl-images-amazon.com/images/I/41rmP2rc-vL._SX342_.jpg"
    },
    {
        "equipmentType": "3D scan",
        "equipmentName": "Hobby",
        "price":  "200 UAH per hour",
        "workingArea": "300x300х300 мм",
        "description": "900 micron",
        "photoUrl": "https://i1.wp.com/3dsourced.com/wp-content/uploads/2018/01/top-15-3d-scanners-for-all-price-range.jpg?resize=850%2C500&ssl=1"
    },
    {
        "equipmentType": "Milling",
        "equipmentName": "Великий фрезер",
        "price":  "420 UAH per hour",
        "workingArea": "1200x800х200 мм",
        "description": "24000RPM",
        "photoUrl": "https://mirstankov.com/wp-content/uploads/2018/02/%D1%84%D1%80%D0%B5%D0%B7%D0%B5%D1%80-MSF-1325.jpg"
    },
    {
        "equipmentType": "Milling",
        "equipmentName": "Roland",
        "price":  "350 UAH per hour",
        "workingArea": "150x150х55 мм",
        "description": "9000 RPM",
        "photoUrl": "https://3d-m.ru/wp-content/uploads/2019/05/frezer-roland-mdx-40a-1.jpg"
    },
    {
        "equipmentType": "CIS",
        "equipmentName": "38 kW",
        "price":  "60 UAH per hour",
        "workingArea": "5 - 10 years",
        "description": "Isengard, Orcside St., 6666",
        "photoUrl": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5EqiCR3SJn5WtKMuJ4VekkCpEut-uxF76GiP_bYrP3-OT6aiw"
    },
    {
        "equipmentType": "Monocrystal",
        "equipmentName": "150 kW",
        "price":  "60 UAH per hour",
        "workingArea": "10+ years",
        "description": "Fangorn, Entling St., 1256",
        "photoUrl": "https://cdn10.bigcommerce.com/s-3yc5xwvk/products/3293/images/4469/VBHN330SA16__61039.1483059860.1280.1280.png?c=2"
    }
];
exports.equipment = functions.https.onRequest((request, response) => {response.send(json);
});
