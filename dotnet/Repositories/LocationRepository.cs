using System.Collections.Generic;
using dungeonbase.Models;
using dungeonbase.Models.Interfaces;
using MongoDB.Driver;

namespace dungeonbase.Repositories
{
    public class LocationRepository
    {
        private readonly IMongoCollection<LocationRecord> _models;

        public LocationRepository(IDungeonbaseDatabaseSettings settings)
        {
            _models = GetDatabase(settings).GetCollection<LocationRecord>(settings.LocationsCollectionName);
        }

        public List<LocationRecord> Get() =>
            _models.Find(model => true).ToList();

        public LocationRecord Get(string id) =>
            _models.Find(model => model.Id == id).FirstOrDefault();

        public LocationRecord GetByName(string Name) =>
            _models.Find(model => model.Name == Name).FirstOrDefault();

        public LocationRecord Create(LocationRecord model)
        {
            _models.InsertOne(model);
            return model;
        }

        public void Update(string id, LocationRecord modelIn) =>
            _models.ReplaceOne(model => model.Id == id, modelIn);

        public void Remove(LocationRecord modelIn) =>
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