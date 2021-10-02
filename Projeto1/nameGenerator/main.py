import petname
import random
from faker import Faker
import json


class Owner:
    def __init__(self, name, address, birthdate, telephone, id, pets):
        self.name = name
        self.address = address
        self.birthdate = birthdate
        self.telephone = telephone
        self.id = id
        self.pets = pets

    def encode(self):
        return self.__dict__


class Pet:
    def __init__(self, name, specie, description, gender, id, weight, birthdate):
        self.name = name
        self.specie = specie
        self.description = description
        self.gender = gender
        self.id = id
        self.weight = weight
        self.birthdate = birthdate

    def encode(self):
        return self.__dict__


def generator(n, m):
    fake = Faker()
    owners = []
    for i in range(0, n):
        # PET
        pets = []
        for j in range(m):
            pets.append(Pet(petname.name(), petname.Generate(), petname.adjective(),
                            'female' if random.random() > 0.5 else 'male', 
                            random.randint(0, 1000000), random.randint(0, 100),fake.date()))

        # OWNER
        owners.append(Owner(fake.name(), fake.address(), fake.date(), fake.phone_number(),
                            random.randint(0, 1000000), pets))

    jsontracks = json.dumps(owners, default=lambda o: o.encode(), indent=4)
    with open(f'owners_{n}_{m}.json', 'w') as f:
        f.write(jsontracks)
    print("Acabei")


generator(10, 1)
generator(10, 10)
generator(10, 100)
generator(10, 1000)
generator(10, 10000)
generator(10, 100000)

generator(1, 1)
generator(10, 10)
generator(100, 100)
generator(1000, 1000)
