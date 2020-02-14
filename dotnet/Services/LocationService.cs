using System.Collections.Generic;
using dungeonbase.Models;
using dungeonbase.Models.Interfaces;
using MongoDB.Driver;

namespace dungeonbase.Services
{
    public class LocationService
    {
        private readonly IMongoCollection<Location> _models;

        public LocationService(IDungeonbaseDatabaseSettings settings)
        {
            _models = GetDatabase(settings).GetCollection<Location>(settings.LocationsCollectionName);
        }

        public List<Location> Get() =>
            _models.Find(model => true).ToList();

        public Location Get(string id) =>
            _models.Find(model => model.Id== id).FirstOrDefault();

        public Location Create(Location model)
        {
            _models.InsertOne(model);
            return model;
        }

        public void Update(string id, Location modelIn) =>
            _models.ReplaceOne(model => model.Id == id, modelIn);

        public void Remove(Location modelIn) =>
            _models.DeleteOne(model => model.Id == modelIn.Id);

        public void Remove(string id) => 
            _models.DeleteOne(model => model.Id == id);

        protected static IMongoDatabase GetDatabase(IDungeonbaseDatabaseSettings settings)
        {
            var client = new MongoClient(settings.ConnectionString);
            return client.GetDatabase(settings.DatabaseName);
        }
    }
}