/*SELECT *
FROM recipes
WHERE recipeID
IN (
SELECT recipeID
FROM ingredients i
INNER JOIN reftable r on i.ingredientID = r.ingredientID
WHERE r.amount > reftable.(required?)
)
GROUP BY recipeID, required
HAVING count(ingredientID) = required*/

select recipeName
from ingredients
inner join reftable
on reftable.ingredientID = ingredients.ingredientID
inner join recipes
on recipes.recipeID = reftable.recipeID
where heldAmt >= reqAmt
group by recipeName having count(reqAmt) >=8
