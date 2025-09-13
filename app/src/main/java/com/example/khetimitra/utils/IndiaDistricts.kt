package com.example.khetimitra.utils

object IndianDistricts {

    val stateDistrictMap = mapOf(
        "Jharkhand" to listOf(
            "Bokaro", "Chatra", "Deoghar", "Dhanbad", "Dumka", "East Singhbhum", "Garhwa", "Giridih",
            "Godda", "Gumla", "Hazaribagh", "Jamtara", "Khunti", "Koderma", "Latehar", "Lohardaga",
            "Pakur", "Palamu", "Ramgarh", "Ranchi", "Sahebganj", "Seraikela Kharsawan", "Simdega", "West Singhbhum"
        ),


        "Andhra Pradesh" to listOf(
            "Anantapur", "Chittoor", "East Godavari", "Guntur", "Krishna",
            "Kurnool", "Nellore", "Prakasam", "Srikakulam", "Visakhapatnam",
            "Vizianagaram", "West Godavari", "YSR Kadapa"
        ),
        "Arunachal Pradesh" to listOf(
            "Tawang", "West Kameng", "East Kameng", "Papum Pare", "Kurung Kumey",
            "Kra Daadi", "Lower Subansiri", "Upper Subansiri", "West Siang", "East Siang",
            "Siang", "Lower Siang", "Upper Siang", "Lower Dibang Valley", "Dibang Valley",
            "Upper Dibang Valley", "Anjaw", "Lohit", "Namsai", "Changlang",
            "Tirap", "Longding", "Itanagar Capital Complex", "Kamle", "Lepa Rada"
        ),
        "Assam" to listOf(
            "Baksa", "Barpeta", "Biswanath", "Bongaigaon", "Cachar", "Charaideo", "Chirang",
            "Darrang", "Dhemaji", "Dhubri", "Dibrugarh", "Dima Hasao", "Goalpara", "Golaghat",
            "Hailakandi", "Hojai", "Jorhat", "Kamrup", "Kamrup Metropolitan", "Karbi Anglong",
            "Karimganj", "Kokrajhar", "Lakhimpur", "Majuli", "Morigaon", "Nagaon", "Nalbari",
            "Sivasagar", "Sonitpur", "South Salmara-Mankachar", "Tinsukia", "Udalguri", "West Karbi Anglong"
        ),
        "Bihar" to listOf(
            "Araria", "Arwal", "Aurangabad", "Banka", "Begusarai", "Bhagalpur", "Bhojpur", "Buxar",
            "Darbhanga", "East Champaran", "Gaya", "Gopalganj", "Jamui", "Jehanabad", "Kaimur",
            "Katihar", "Khagaria", "Kishanganj", "Lakhisarai", "Madhepura", "Madhubani", "Munger",
            "Muzaffarpur", "Nalanda", "Nawada", "Patna", "Purnia", "Rohtas", "Saharsa", "Samastipur",
            "Saran", "Sheikhpura", "Sheohar", "Sitamarhi", "Siwan", "Supaul", "Vaishali", "West Champaran"
        ),

        "Maharashtra" to listOf(
            "Mumbai City", "Mumbai Suburban", "Pune", "Nagpur", "Thane", "Aurangabad", "Ahmednagar",
            "Solapur", "Kolhapur", "Nashik", "Satara", "Ratnagiri", "Sindhudurg", "Jalgaon",
            "Jalna", "Parbhani", "Latur", "Beed", "Buldhana", "Chandrapur", "Gadchiroli",
            "Gondia", "Hingoli", "Wardha", "Yavatmal", "Raigad", "Palghar"
        ),

        "Uttar Pradesh" to listOf(
        "Agra", "Aligarh", "Prayagraj", "Ambedkar Nagar", "Amethi", "Amroha", "Auraiya", "Ayodhya",
        "Azamgarh", "Baghpat", "Bahraich", "Ballia", "Balrampur", "Banda", "Barabanki", "Bareilly",
        "Basti", "Bhadohi", "Bijnor", "Bulandshahr", "Chandauli", "Chitrakoot", "Deoria", "Etah",
        "Etawah", "Ayodhya", "Faizabad", "Farrukhabad", "Fatehpur", "Firozabad", "Gautam Buddha Nagar",
        "Ghazipur", "Gonda", "Gorakhpur", "Hamirpur", "Hapur", "Hardoi", "Hathras", "Jalaun",
        "Jaunpur", "Jhansi", "Kannauj", "Kanpur Dehat", "Kanpur Nagar", "Kasganj", "Kaushambi",
        "Kheri", "Kushinagar", "Lakhimpur Kheri", "Lalitpur", "Lucknow", "Maharajganj", "Mahoba",
        "Mainpuri", "Mathura", "Mau", "Meerut", "Mirzapur", "Moradabad", "Muzaffarnagar", "Pilibhit",
        "Pratapgarh", "Raebareli", "Rampur", "Saharanpur", "Sambhal", "Sant Kabir Nagar", "Shahjahanpur",
        "Shamli", "Shrawasti", "Siddharthnagar", "Sitapur", "Sonbhadra", "Sultanpur", "Unnao", "Varanasi",
        ),

        "Chhattisgarh" to listOf(
            "Balod", "Baloda Bazar", "Balrampur", "Bemetara", "Bijapur", "Bilaspur", "Dantewada", "Dhamtari",
            "Durg", "Gariaband", "Gaurela-Pendra-Marwahi", "Janjgir-Champa", "Jashpur", "Kabirdham", "Kanker",
            "Kondagaon", "Korba", "Koriya", "Mahasamund", "Mungeli", "Narayanpur", "Raigarh", "Raipur",
            "Rajnandgaon", "Sukma", "Surajpur", "Surguja",

            ),

        "Haryana" to listOf(
            "Ambala", "Bhiwani", "Charkhi Dadri", "Faridabad", "Fatehabad", "Gurgaon", "Hisar", "Jhajjar",
            "Jind", "Kaithal", "Karnal", "Kurukshetra", "Mahendragarh", "Mewat", "Palwal", "Panchkula",
            "Panipat", "Rewari", "Rohtak", "Sirsa", "Sonipat", "Yamunanagar",
        ),

        "Gujarat" to listOf(
            "Ahmedabad", "Amreli", "Anand", "Aravalli", "Banaskantha", "Bharuch", "Bhavnagar", "Botad",
            "Chhota Udaipur", "Dahod", "Dang", "Gandhinagar", "Gir Somnath", "Jamnagar", "Junagadh",
            "Kachchh", "Kheda", "Mahisagar", "Mehsana", "Morbi", "Narmada", "Navsari", "Panchmahal",
            "Patan", "Porbandar", "Rajkot", "Sabarkantha", "Surat", "Surendranagar", "Tapi", "Vadodara", "Valsad",
            ),

        "Himachal Pradesh" to listOf(
            "Bilaspur", "Chamba", "Hamirpur", "Kangra", "Kinnaur", "Kullu", "Lahaul & Spiti", "Mandi",
            "Shimla", "Sirmaur", "Solan", "Una",
            ),

        "Punjab" to listOf(
            "Amritsar", "Barnala", "Bathinda", "Faridkot", "Fatehgarh Sahib", "Fazilka", "Ferozepur",
            "Gurdaspur", "Hoshiarpur", "Jalandhar", "Kapurthala", "Ludhiana", "Mansa", "Moga",
            "Pathankot", "Patiala", "Rupnagar", "S.A.S. Nagar", "Sangrur", "Tarn Taran",

            ),

        "Jammu & Kashmir" to listOf(
                "Anantnag", "Bandipora", "Baramulla", "Budgam", "Doda", "Ganderbal", "Jammu", "Kishtwar",
                "Kulgam", "Kupwara", "Poonch", "Pulwama", "Rajouri", "Ramban", "Reasi", "Samba", "Shopian", "Srinagar", "Udhampur",

        ),

        "Manipur" to listOf(
            "Imphal East", "Imphal West", "Bishnupur", "Chandel", "Churachandpur", "Senapati",
            "Tamenglong", "Thoubal", "Ukhrul", "Jiribam",
            ),

        "Meghalaya" to listOf(
            "East Garo Hills", "East Jaintia Hills", "East Khasi Hills", "North Garo Hills",
            "Ri Bhoi", "South Garo Hills", "South West Garo Hills", "South West Khasi Hills",
            "West Garo Hills", "West Jaintia Hills", "West Khasi Hills",
            ),

        "Tamil Nadu" to listOf(
            "Ariyalur", "Chengalpattu", "Chennai", "Coimbatore", "Cuddalore", "Dharmapuri",
            "Dindigul", "Erode", "Kallakurichi", "Kanchipuram", "Kanyakumari", "Karur",
            "Krishnagiri", "Madurai", "Nagapattinam", "Namakkal", "Perambalur", "Pudukkottai",
            "Ramanathapuram", "Ranipet", "Salem", "Sivaganga", "Tenkasi", "Thanjavur", "Theni",
            "Thoothukudi", "Tiruchirappalli", "Tirunelveli", "Tirupathur", "Tiruppur", "Tiruvallur",
            "Tiruvannamalai", "Tiruvarur", "Vellore", "Viluppuram", "Virudhunagar",
            ),

        "Delhi" to listOf(
            "Central Delhi", "East Delhi", "New Delhi", "North Delhi", "North East Delhi", "North West Delhi",
            "Shahdara", "South Delhi", "South East Delhi", "South West Delhi", "West Delhi",
        ),

        "West Bengal" to listOf(
            "Alipurduar", "Bankura", "Birbhum", "Cooch Behar", "Dakshin Dinajpur", "Darjeeling", "Hooghly",
            "Howrah", "Jalpaiguri", "Jhargram", "Kalimpong", "Kolkata", "Malda", "Murshidabad", "Nadia",
            "North 24 Parganas", "Paschim Bardhaman", "Paschim Medinipur", "Purba Bardhaman", "Purba Medinipur",
            "Purulia", "South 24 Parganas", "Uttar Dinajpur",
            ),

        "Uttarakhand" to listOf(
            "Almora", "Bageshwar", "Chamoli", "Champawat", "Dehradun", "Haridwar", "Nainital",
            "Pauri Garhwal", "Pithoragarh", "Rudraprayag", "Tehri Garhwal", "Udham Singh Nagar", "Uttarkashi",
            ),

        "Tripura" to listOf(
            "West Tripura", "Khowai", "Sipahijala", "South Tripura", "Dhalai", "North Tripura", "Gomati", "Unakoti",
            ),

        "Telangana" to listOf(
            "Adilabad", "Bhadradri Kothagudem", "Hyderabad", "Jagtial", "Jangaon", "Jayashankar Bhupalpally",
            "Jogulamba Gadwal", "Kamareddy", "Karimnagar", "Khammam", "Komaram Bheem Asifabad", "Mahabubabad",
            "Mahabubnagar", "Mancherial", "Medak", "Medchal Malkajgiri", "Mulugu", "Nagarkurnool", "Nalgonda",
            "Nirmal", "Nizamabad", "Peddapalli", "Rajanna Sircilla", "Rangareddy", "Sangareddy", "Siddipet",
            "Suryapet", "Vikarabad", "Wanaparthy", "Warangal (Rural)", "Warangal (Urban)", "Yadadri Bhuvanagiri",
            ),

        "Sikkim" to listOf(
            "East Sikkim", "North Sikkim", "South Sikkim", "West Sikkim",
            ),

        "Rajasthan" to listOf(
            "Ajmer", "Alwar", "Banswara", "Baran", "Barmer", "Bharatpur", "Bhilwara", "Bikaner",
            "Bundi", "Chittorgarh", "Churu", "Dausa", "Dholpur", "Dungarpur", "Hanumangarh",
            "Jaipur", "Jaisalmer", "Jalore", "Jhalawar", "Jhunjhunu", "Jodhpur", "Karauli",
            "Kota", "Nagaur", "Pali", "Pratapgarh", "Rajsamand", "Sawai Madhopur", "Sikar",
            "Sirohi", "Tonk", "Udaipur",

            ),

        "Odisha" to listOf(
            "Angul", "Balangir", "Balasore", "Bargarh", "Bhadrak", "Boudh", "Cuttack", "Deogarh",
            "Dhenkanal", "Gajapati", "Ganjam", "Jagatsinghpur", "Jajpur", "Jharsuguda", "Kalahandi",
            "Kandhamal", "Kendrapara", "Kendujhar", "Khordha", "Koraput", "Malkangiri", "Mayurbhanj",
            "Nabarangpur", "Nuapada", "Puri", "Rayagada", "Sambalpur", "Sonepur", "Sundargarh",
            ),

        "Nagaland" to listOf(
            "Dimapur", "Kiphire", "Kohima", "Longleng", "Mokokchung", "Mon", "Peren", "Phek",
            "Tuensang", "Wokha", "Zunheboto",

            ),

        "Mizoram" to listOf(
            "Aizawl", "Champhai", "Kolasib", "Lawngtlai", "Lunglei", "Mamit", "Saiha", "Serchhip",
            ),

        "Ladakh" to listOf(
            "Kargil", "Leh",
        ),

        "Goa" to listOf(
            "North Goa", "South Goa",
        ),

    )

}

